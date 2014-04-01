package com.organization4242.tictactoe.app;

import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGame;
import com.organization4242.tictactoe.model.TicTacToeModel;
import com.organization4242.tictactoe.view.TicTacToeStartScreen;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeGame extends AndroidGame {
    private AbstractController controller = new TicTacToeController();

    private TicTacToeModel model;
    private TicTacToeStartScreen screen;

    public void setModel(TicTacToeModel model) {
        this.model = model;
        controller.addModel(this.model);
    }

    public TicTacToeGame() {
        setModel(new TicTacToeModel());
    }

    @Override
    public Screen getStartScreen() {
        setScreen(new TicTacToeStartScreen());
        controller.addView(getCurrentScreen());
        return getCurrentScreen();
    }
}