package com.organization4242.tictactoe.model;

import java.util.ArrayList;

/**
 * Created by ilya on 04.04.14.
 */
public class TicTacToeField extends ArrayList<State> implements Field {
    public TicTacToeField(int capacity) {
        super(capacity);
    }

    @Override
    public State getWinner() {
        return null;
    }

    @Override
    public boolean hasWinner() {
        return false;
    }

    @Override
    public byte[] getEmptyFields() {
        return new byte[0];
    }

    @Override
    public boolean add(State object) {
        super.add(object);
        return false;
    }

    @Override
    public State get(int index) {
        return super.get(index);
    }

    @Override
    public State set(int index, State object) {
        super.set(index, object);
        return object;
    }
}
