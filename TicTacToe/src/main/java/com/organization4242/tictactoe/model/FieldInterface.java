package com.organization4242.tictactoe.model;

/**
 * Created by ilya on 04.04.14.
 */
public interface FieldInterface {
    void add(State state);
    State get(int index);
    void set(int index, State state);

    State getWinner();
    boolean hasWinner();

    byte[] getEmptyFields();
}
