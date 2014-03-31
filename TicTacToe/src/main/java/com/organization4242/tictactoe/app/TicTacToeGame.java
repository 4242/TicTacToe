package com.organization4242.tictactoe.app;

import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGame;
import com.organization4242.tictactoe.model.TicTacToeModel;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeGame extends AndroidGame {
    private TicTacToeModel model = new TicTacToeModel();

    public void setModel(TicTacToeModel model) {
        this.model = model;
    }

    public TicTacToeGame() {
        model.addListener(getCurrentScreen());
    }

    @Override
    public Screen getStartScreen() {
        setScreen(new TicTacToeStartScreen(this));
        getCurrentScreen().addListener(model);
        return getCurrentScreen();
    }
}