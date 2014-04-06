package com.organization4242.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 04.04.14.
 */
public class Field implements FieldInterface {
    private ArrayList<State> list;

    private boolean checkWinner(State order) {
        ArrayList<Byte> list = new ArrayList<Byte>(MainFieldModel.NUMBER_OF_FIELDS);
        for (State state : this.list) {
            list.add(State.toByte(state));
        }

        return list.get(0) + list.get(1) + list.get(2) == 3 * State.toByte(order)
                || list.get(3) + list.get(4) + list.get(5) == 3 * State.toByte(order)
                || list.get(6) + list.get(7) + list.get(8) == 3 * State.toByte(order)
                || list.get(0) + list.get(4) + list.get(8) == 3 * State.toByte(order)
                || list.get(2) + list.get(4) + list.get(6) == 3 * State.toByte(order)
                || list.get(6) + list.get(7) + list.get(8) == 3 * State.toByte(order)
                || list.get(0) + list.get(3) + list.get(6) == 3 * State.toByte(order)
                || list.get(1) + list.get(4) + list.get(7) == 3 * State.toByte(order)
                || list.get(2) + list.get(5) + list.get(8) == 3 * State.toByte(order);

    }

    public Field(int capacity) {
        list = new ArrayList<State>(capacity);
    }

    @Override
    public State getWinner() {
        if (checkWinner(State.X)) {
            return State.X;
        } else if (checkWinner(State.O)) {
            return State.O;
        }
        return State.EMPTY;
    }

    @Override
    public boolean hasWinner() {
        return checkWinner(State.O) || checkWinner(State.X);
    }

    @Override
    public List<Byte> getEmptyFields() {
        List<Byte> emptyFields = new ArrayList<Byte>();
        for (int i = 0; i < list.size(); i++) {
            State state = list.get(i);
            if (state == State.EMPTY) {
                emptyFields.add((byte) i);
            }
        }
        return emptyFields;
    }

    @Override
    public void add(State object) {
        list.add(object);
    }

    @Override
    public State get(int index) {
        return list.get(index);
    }

    @Override
    public void set(int index, State object) {
        list.set(index, object);
    }

    @Override
    public int size() {
        return list.size();
    }
}
