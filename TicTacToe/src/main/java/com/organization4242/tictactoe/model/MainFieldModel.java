package com.organization4242.tictactoe.model;

import android.util.Log;
import com.organization4242.tictactoe.ai.AI;
import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.controller.Controller;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 31.03.14.
 */
public final class MainFieldModel extends AbstractModel {
    public static final int NUMBER_OF_FIELDS = 9;
    public static final Byte ANY = 10;

    private static final String TAG = "Main Window Model: ";

    private State order;
    private byte activeField;
    private Map<State, Byte> previousFields;
    private FieldContainer baseField;
    private List<FieldInterface> fields;

    private AI ai;

    private static MainFieldModel instance = new MainFieldModel();

    public byte getActiveField() {
        return activeField;
    }

    public FieldContainer getBaseField() {
        return baseField;
    }

    public List<FieldInterface> getFields() {
        return fields;
    }

    public State getOrder() {
        return order;
    }

    public static MainFieldModel getInstance() {
        return instance;
    }

    public void setOrder(State order) {
        this.order = order;
    }

    public void setActiveField(byte activeField) {
        this.activeField = activeField;
    }

    public void setBaseField(FieldContainer baseField) {
        this.baseField = baseField;
    }

    public void setFields(List<FieldInterface> fields) {
        this.fields = fields;
    }

    private MainFieldModel() {
        activeField = ANY;
        previousFields = new HashMap<State, Byte>();
        previousFields.put(State.X, ANY);
        previousFields.put(State.O, ANY);
        order = State.X;
        baseField = new FieldContainer(NUMBER_OF_FIELDS);
        fields = new ArrayList<FieldInterface>(NUMBER_OF_FIELDS);
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            baseField.add(State.EMPTY);
            fields.add(new Field(NUMBER_OF_FIELDS));
            for (int j = 0; j < NUMBER_OF_FIELDS; j++) {
                fields.get(i).add(State.EMPTY);
            }
            baseField.add(fields.get(i));
        }
        ai = new TicTacToeAI(this);
    }

    public boolean canMove(byte i, byte j, State order) {
        return (activeField == i || activeField == ANY)
                && fields.get(i).get(j).equals(State.EMPTY)
                && (j != previousFields.get(order)
                    || fields.get(i).getEmptyFields().size() == 1);
    }

    private void makeMove(byte i, byte j) {
        Log.d(TAG, String.format("Making move to %d, %d", i, j));

        String propertyName = Controller.MODEL_UPDATED;
        fields.get(i).set(j, order);
        State winner = fields.get(i).getWinner();
        activeField = j;

        if (fields.get(j).getEmptyFields().size() == 0) {
            activeField = ANY;
        }

        if (winner != State.EMPTY) {
            baseField.set(i, winner);
            propertyName = Controller.LOCAL_WIN;
            if (baseField.getWinner() != State.EMPTY) {
                propertyName = Controller.WIN;
            }
        }

        MainFieldModelMessage message =
                new MainFieldModelMessage(i, j, activeField, previousFields.get(order), order);
        firePropertyChange(propertyName, 0, message);
        previousFields.put(order, j);
        order = State.reverse(order);
    }

    private void clear() {
        instance = new MainFieldModel();
    }

    @Override
    public void viewPropertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(Controller.VIEW_UPDATED)) {
            byte[] coordinates = (byte[]) pce.getNewValue();
            if (canMove(coordinates[0], coordinates[1], order)) {
                makeMove(coordinates[0], coordinates[1]);
                byte move = ai.nextMove();
                makeMove(ai.getActiveField(), move);
            }
        } else if (pce.getPropertyName().equals(Controller.DISPOSING)) {
            clear();
        }
    }
}
