package com.organization4242.tictactoe.framework.implementations;

import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.Observer;

/**
 * Created by ilya on 06.04.14.
 */
public class ObservableTouchHandler extends SingleTouchHandler {
    private Observable o = new Observable();
    private int[] coordinates;
    public ObservableTouchHandler(View view, float scaleX, float scaleY) {
        super(view, scaleX, scaleY);
    }

    public void addObserver(Observer observer) {
        o.addObserver(observer);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean b = super.onTouch(v, event);
        int[] newCoordinates = new int[]{super.getTouchX(0), super.getTouchY(0)};
        if (!Arrays.equals(coordinates, newCoordinates)) {
            coordinates = newCoordinates;
            o.send(coordinates);
        }
        return b;
    }

    private class Observable extends java.util.Observable {
        public void send(int[] coordinates) {
            setChanged();
            notifyObservers(coordinates);
        }
    }
}
