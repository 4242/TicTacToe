package com.organization4242.tictactoe.app;

import com.organization4242.tictactoe.controller.Controller;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGame;
import com.organization4242.tictactoe.model.MainFieldModel;
import com.organization4242.tictactoe.view.GameScreen;

/**
 * Created by ilya on 30.03.14.
 */
public class Game extends AndroidGame {
    private Controller controller = new Controller();
    private GameScreen screen;

    public Game() {
        controller.addModel(MainFieldModel.getInstance());
    }

    @Override
    public Screen getStartScreen() {
        setScreen(new GameScreen());
        controller.addView(getCurrentScreen());
        return getCurrentScreen();
    }
}