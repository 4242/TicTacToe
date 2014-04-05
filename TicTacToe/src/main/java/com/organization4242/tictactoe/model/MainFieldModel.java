package com.organization4242.tictactoe.model;

import com.organization4242.tictactoe.controller.Controller;
import com.organization4242.tictactoe.view.MainFieldModelMessage;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public final class MainFieldModel extends AbstractModel {
    public static final int NUMBER_OF_FIELDS = 9;
    public static final Byte ANY = 10;

    private State order;
    private byte activeField;
    private byte previousField;
    private FieldInterface baseField;
    private List<FieldInterface> fields;

    private MainFieldModelMessage message;

    private static MainFieldModel instance = new MainFieldModel();

    public byte getActiveField() {
        return activeField;
    }

    public FieldInterface getBaseField() {
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

    private MainFieldModel() {
        activeField = ANY;
        previousField = ANY;
        order = State.X;
        baseField = new Field(NUMBER_OF_FIELDS);
        fields = new ArrayList<FieldInterface>(NUMBER_OF_FIELDS);
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            baseField.add(State.EMPTY);
            fields.add(new Field(NUMBER_OF_FIELDS));
            for (int j = 0; j < NUMBER_OF_FIELDS; j++) {
                fields.get(i).add(State.EMPTY);
            }
        }
    }

    public boolean canMove(byte i, byte j) {
        return (activeField == i || activeField == ANY)
                && fields.get(i).get(j).equals(State.EMPTY);
    }

    private void makeMove(byte i, byte j) {
        String propertyName = Controller.MODEL_UPDATED;
        fields.get(i).set(j, order);
        State winner = fields.get(i).getWinner();
        activeField = j;

        if (fields.get(i).getEmptyFields().size() == 0) {
            activeField = ANY;
        }

        if (winner != State.EMPTY) {
            baseField.set(i, winner);
            propertyName = Controller.LOCAL_WIN;
            if (baseField.getWinner() != State.EMPTY) {
                propertyName = Controller.WIN;
            }
        }

        message = new MainFieldModelMessage(i, j, activeField, order);
        firePropertyChange(propertyName, 0, message);
        order = State.reverse(order);
    }

    private void clear() {
        instance = new MainFieldModel();
    }

    @Override
    public void viewPropertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(Controller.VIEW_UPDATED)) {
            byte[] coordinates = (byte[]) pce.getNewValue();
            if (canMove(coordinates[0], coordinates[1])) {
                makeMove(coordinates[0], coordinates[1]);
            }
        } else if (pce.getPropertyName().equals(Controller.DISPOSING)) {
            clear();
        }
    }
}
