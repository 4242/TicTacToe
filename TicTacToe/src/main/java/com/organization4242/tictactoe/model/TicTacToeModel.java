package com.organization4242.tictactoe.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public class TicTacToeModel implements PropertyChangeListener {
    public static String UPDATE = "Update";

    //true - X, false - O
    private boolean order;
    private List<Integer> baseField;
    private List<Integer> fields;

    PropertyChangeSupport pce = new PropertyChangeSupport(this);

    public boolean isOrder() {
        return order;
    }

    public List<Integer> getBaseField() {
        return baseField;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        baseField.set(1, 1);

    }

    private void updateView() {
        pce.firePropertyChange("asdasdasd", 1, 0);
    }
}
