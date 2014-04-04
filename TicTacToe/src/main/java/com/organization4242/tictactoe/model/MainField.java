package com.organization4242.tictactoe.model;

import com.organization4242.tictactoe.app.Controller;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public final class MainField extends AbstractModel {
    public static final int NUMBER_OF_FIELDS = 9;
    public static final Byte ANY = 10;

    private State order;
    private byte activeField;
    private byte previousField;
    private Field baseField;
    private List<Field> fields;

    private static MainField instance = new MainField();

    public byte getActiveField() {
        return activeField;
    }

    public Field getBaseField() {
        return baseField;
    }

    public List<Field> getFields() {
        return fields;
    }

    public State getOrder() {
        return order;
    }

    public static MainField getInstance() {
        return instance;
    }

    private MainField() {
        activeField = ANY;
        previousField = ANY;
        order = State.X;
        baseField = new TicTacToeField(NUMBER_OF_FIELDS);
        fields = new ArrayList<Field>(NUMBER_OF_FIELDS);
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            fields.add(new TicTacToeField(NUMBER_OF_FIELDS));
            for (int j = 0; j < NUMBER_OF_FIELDS; j++) {
                fields.get(i).add(State.EMPTY);
            }
        }
    }

    public boolean canMove(byte i, byte j) {
        return activeField == i
                && fields.get(i).get(j).equals(State.EMPTY);
    }

    private void makeMove(byte i, byte j) {
        fields.get(i).set(j, order);
        firePropertyChange(Controller.MODEL_UPDATED, 0, 1);
    }

    @Override
    public void viewPropertyChange(PropertyChangeEvent pce) {
        byte[] coordinates = (byte[]) pce.getNewValue();
        makeMove(coordinates[0], coordinates[1]);
    }
}
