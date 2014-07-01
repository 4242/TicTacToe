package com.organization4242.tictactoe.app;

import com.organization4242.tictactoe.controller.Controller;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGame;
import com.organization4242.tictactoe.model.AbstractModel;
import com.organization4242.tictactoe.model.MainFieldModel;
import com.organization4242.tictactoe.view.GameScreen;

import java.beans.PropertyChangeEvent;

/**
 * Created by ilya on 30.03.14.
 */
public class Game extends AndroidGame {
    private Controller controller = new Controller();
    private GameScreen screen;

    public Game() {
        controller.addModel(MainFieldModel.getInstance());
        new Listener();
    }

    private class Listener extends AbstractModel {
        Listener() {
            controller.addModel(this);
        }

        @Override
        public void viewPropertyChange(PropertyChangeEvent pce) {

        }
    }

    @Override
    public Screen getStartScreen() {
        setScreen(new GameScreen());
        controller.addView(getCurrentScreen());
        return getCurrentScreen();
    }
}