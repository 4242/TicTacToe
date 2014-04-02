package com.organization4242.tictactoe.view;

import android.graphics.Color;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Input;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;
import com.organization4242.tictactoe.framework.implementations.AndroidInput;
import com.organization4242.tictactoe.model.MainField;

import java.beans.PropertyChangeEvent;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeStartScreen extends Screen {
    public static String INPUT_EVENT = "InputEvent";

    private MainField mainField;

    public TicTacToeStartScreen() {

    }

    private int getTouchMainField(Input.TouchEvent event) {
        return event.getX()/600 + (event.getY()/800) *3 ;
    }

    @Override
    public void update(float deltaTime) {
        if (AndroidInput.getInstance().getTouchEvents().size() != 0) {
            Object x = AndroidInput.getInstance().getTouchX(Input.TouchEvent.TOUCH_DOWN);
            firePropertyChange(INPUT_EVENT, 0, new Object[] {
                    x
            });
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = AndroidGraphics.getInstance();
        int cl = 0;
        for (int i = 0; i < MainField.getInstance().getBaseField().size(); i++) {
            switch (MainField.getInstance().getBaseField().get(i)) {
                case -1 :
                    cl = Color.BLUE;
                    break;
                case 0 :
                    cl = Color.WHITE;
                    break;
                case 1 :
                    cl = Color.RED;
                    break;
            }
            g.drawRect((i/3)*200, (i%3)*200, 200, 200, cl);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++) {
                    switch (MainField.getInstance().getFields().get(i).get(j).get(k)) {
                        case -1:
                            cl = Color.BLUE;
                            break;
                        case 0:
                            cl = Color.GRAY;
                            break;
                        case 1:
                            cl = Color.RED;
                            break;
                    }
                g.drawRect(10 + (i / 3) * 200 + k*60,
                                            10 + (i % 3) * 200 + j*60,
                                            55, 55, cl);
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent pce) {

    }
}