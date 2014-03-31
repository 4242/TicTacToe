package com.organization4242.tictactoe.model;

import android.util.Log;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by ilya on 31.03.14.
 */
public class TicTacToeModel implements PropertyChangeListener {
    public static String UPDATE = "Update";
    private MainField mainField = new MainField();

    PropertyChangeSupport pce = new PropertyChangeSupport(this);

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Log.d("", Arrays.toString(((Object[]) propertyChangeEvent.getNewValue())));
    }

    private void updateView() {
        pce.firePropertyChange(TicTacToeModel.UPDATE, 0, mainField);
    }

    public void addListener(PropertyChangeListener listener) {
        pce.addPropertyChangeListener(listener);
    }
}
