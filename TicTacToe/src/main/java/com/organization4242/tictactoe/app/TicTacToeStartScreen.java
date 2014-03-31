package com.organization4242.tictactoe.app;

import android.graphics.Color;
import com.organization4242.tictactoe.framework.Game;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Input;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.model.MainField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeStartScreen extends Screen implements PropertyChangeListener{
    public static String INPUT_EVENT = "InputEvent";

    private Game game;
    private Input input;
    private MainField mainField;

    TicTacToeStartScreen(Game game) {
        super(game);
        this.game = game;
        input = game.getInput();
        final ArrayList<Integer> tmp1 = new ArrayList<Integer>() {
            {
                add(0); add(0); add(0);
            }
        };
        final ArrayList<ArrayList<Integer>> tmp2 = new ArrayList<ArrayList<Integer>>() {
            {
                add(tmp1); add(tmp1); add(tmp1);
            }
        };
        final ArrayList<ArrayList<ArrayList<Integer>>> tmp3 = new ArrayList<ArrayList<ArrayList<Integer>>>() {
            {
                add(tmp2); add(tmp2); add(tmp2);
                add(tmp2); add(tmp2); add(tmp2);
                add(tmp2); add(tmp2); add(tmp2);
            }
        };

        mainField = new MainField().setBaseField(new ArrayList<Integer>() {
            {
                add(0);
                add(0);
                add(0);
                add(0);
                add(0);
                add(0);
                add(0);
                add(0);
                add(0);
            }
        }).setFields(tmp3);
    }

    private int getTouchMainField(Input.TouchEvent event) {
        return event.getX()/600 + (event.getY()/800) *3 ;
    }

    @Override
    public void update(float deltaTime) {
        if (input.getTouchEvents().size() != 0) {
            Object x = input.getTouchX(Input.TouchEvent.TOUCH_DOWN);
            firePropertyChange(INPUT_EVENT, 0, new Object[] {
                    x
            });
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        int cl = 0;
        for (int i = 0; i<mainField.getBaseField().size(); i++) {
            switch (mainField.getBaseField().get(i)) {
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
            game.getGraphics().drawRect((i/3)*200, (i%3)*200, 200, 200, cl);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++) {
                    switch (mainField.getFields().get(i).get(j).get(k)) {
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
                game.getGraphics().drawRect(10 + (i / 3) * 200 + k*60,
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
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        this.mainField = (MainField) propertyChangeEvent.getNewValue();
    }
}