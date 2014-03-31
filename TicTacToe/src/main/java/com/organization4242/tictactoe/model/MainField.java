package com.organization4242.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public class MainField {
    private boolean order;
    private int activeField;
    private List<Integer> baseField;
    private ArrayList<ArrayList<ArrayList<Integer>>> fields;

    public MainField setOrder(boolean order) {
        this.order = order;
        return this;
    }

    public MainField setActiveField(int activeField) {
        this.activeField = activeField;
        return this;
    }

    public MainField setBaseField(List<Integer> baseField) {
        this.baseField = baseField;
        return this;
    }

    public MainField setFields(ArrayList<ArrayList<ArrayList<Integer>>> fields) {
        this.fields = fields;
        return this;
    }

    public boolean getOrder() {
        return order;
    }

    public int getActiveField() {
        return activeField;
    }

    public List<Integer> getBaseField() {
        return baseField;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getFields() {
        return fields;
    }
}
