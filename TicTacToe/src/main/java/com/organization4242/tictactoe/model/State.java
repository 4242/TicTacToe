package com.organization4242.tictactoe.model;

import java.security.InvalidParameterException;

/**
 * Created by ilya on 04.04.14.
 */
public enum State {
    X,
    O,
    EMPTY;

    public static State fromByte(byte b) {
        if (b == 1) {
            return X;
        } else if (b == -1) {
            return O;
        } else if (b == 0) {
            return EMPTY;
        } else {
            throw new InvalidParameterException();
        }
    }

    public static byte toByte(State state) {
        if (state == X) {
            return 1;
        } else if (state == O) {
            return -1;
        } else {
            return 0;
        }
    }

    public static State reverse(State initialState) {
        if (initialState == X) {
            return O;
        } else {
            return X;
        }
    }
}
