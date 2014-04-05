package com.organization4242.tictactoe.view;

import com.organization4242.tictactoe.model.State;

/**
 * Created by ilya on 05.04.14.
 */
public class MainFieldModelMessage {
    private byte i;
    private byte j;
    private byte activeField;
    private State order;

    public MainFieldModelMessage(byte i, byte j, byte activeField, State order) {
        this.i = i;
        this.j = j;
        this.activeField = activeField;
        this.order = order;
    }

    public byte getI() {
        return i;
    }

    public byte getJ() {
        return j;
    }

    public byte getActiveField() {
        return activeField;
    }

    public State getOrder() {
        return order;
    }
}
