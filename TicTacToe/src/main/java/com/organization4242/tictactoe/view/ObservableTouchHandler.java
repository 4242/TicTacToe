package com.organization4242.tictactoe.view;

import android.view.MotionEvent;
import android.view.View;
import com.organization4242.tictactoe.framework.implementations.SingleTouchHandler;

import java.util.Observer;

/**
 * Created by ilya on 06.04.14.
 */
public class ObservableTouchHandler extends SingleTouchHandler {
    private Observable o = new Observable();
    public ObservableTouchHandler(View view, float scaleX, float scaleY) {
        super(view, scaleX, scaleY);
    }

    public void addObserver(Observer observer) {
        o.addObserver(observer);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean b = super.onTouch(v, event);
        o.send(new int[] {super.getTouchX(0), super.getTouchY(0)});
        return b;
    }

    private class Observable extends java.util.Observable {
        public void send(int[] coordinates) {
            setChanged();
            notifyObservers(coordinates);
        }
    }
}
