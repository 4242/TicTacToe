package com.organization4242.tictactoe.view;

import android.graphics.Color;
import com.organization4242.tictactoe.model.State;

import java.security.InvalidParameterException;

/**
 * Created by ilya on 05.04.14.
 */
public class FieldColor {
    public static int fromState (State state) {
        if (state == State.X) {
            return Color.RED;
        } else if (state == State.O) {
            return Color.BLUE;
        } else {
            return Color.WHITE;
        }
    }

    public static int fromByte(byte b) {
        if (b == 1) {
            return Color.RED;
        } else if (b == -1) {
            return Color.BLUE;
        } else if (b == 0) {
            return Color.WHITE;
        } else {
            throw new InvalidParameterException();
        }
    }
}
