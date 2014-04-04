package com.organization4242.tictactoe.model;

/**
 * Created by ilya on 04.04.14.
 */
public interface Field {
    boolean add(State state);
    State get(int index);
    State set(int index, State state);

    State getWinner();
    boolean hasWinner();

    byte[] getEmptyFields();
}
