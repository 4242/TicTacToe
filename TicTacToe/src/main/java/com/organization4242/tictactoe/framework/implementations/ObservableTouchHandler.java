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
    private int[] downCoordinates;

    public ObservableTouchHandler(View view, float scaleX, float scaleY) {
        super(view, scaleX, scaleY);
    }

    public void addObserver(Observer observer) {
        o.addObserver(observer);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean b = super.onTouch(v, event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downCoordinates = new int[]{super.getTouchX(0), super.getTouchY(0)};
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int[] upCoordinates = new int[]{super.getTouchX(0), super.getTouchY(0)};
            if (Arrays.equals(upCoordinates, downCoordinates)) {
                o.send(upCoordinates);
            }
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