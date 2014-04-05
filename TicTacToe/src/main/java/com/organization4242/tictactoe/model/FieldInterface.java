package com.organization4242.tictactoe.model;

import java.util.List;

/**
 * Created by ilya on 04.04.14.
 */
public interface FieldInterface {
    void add(State state);
    State get(int index);
    void set(int index, State state);

    int size();

    State getWinner();
    boolean hasWinner();

    List<Byte> getEmptyFields();
}
