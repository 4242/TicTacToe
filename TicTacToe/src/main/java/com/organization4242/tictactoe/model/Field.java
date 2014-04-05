package com.organization4242.tictactoe.model;

import java.util.ArrayList;

/**
 * Created by ilya on 04.04.14.
 */
public class Field implements FieldInterface {
    private ArrayList<State> list;

    public Field(int capacity) {
        list = new ArrayList<State>(capacity);
    }

    @Override
    public State getWinner() {
        return State.X;
    }

    @Override
    public boolean hasWinner() {
        return false;
    }

    @Override
    public byte[] getEmptyFields() {
        return new byte[1];
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
}
