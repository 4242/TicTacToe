package com.organization4242.tictactoe.model;

/**
 * Created by ilya on 05.04.14.
 */
public class MainFieldModelMessage {
    private byte i;
    private byte j;
    private byte activeField;
    private byte previousField;
    private State order;

    public MainFieldModelMessage(byte i, byte j, byte activeField, byte previousField, State order) {
        this.i = i;
        this.j = j;
        this.activeField = activeField;
        this.previousField = previousField;
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

    public byte getPreviousField() {
        return previousField;
    }

    public State getOrder() {
        return order;
    }
}