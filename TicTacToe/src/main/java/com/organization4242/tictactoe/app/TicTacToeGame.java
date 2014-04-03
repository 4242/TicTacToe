package com.organization4242.tictactoe.app;

import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGame;
import com.organization4242.tictactoe.model.MainField;
import com.organization4242.tictactoe.view.TicTacToeStartScreen;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeGame extends AndroidGame {
    private TicTacToeController controller = new TicTacToeController();
    private TicTacToeStartScreen screen;

    public TicTacToeGame() {
        MainField.getInstance().setOrder(MainField.X);
        controller.addModel(MainField.getInstance());
        controller.setAi(new TicTacToeAI());
    }

    @Override
    public Screen getStartScreen() {
        setScreen(new TicTacToeStartScreen());
        controller.addView(getCurrentScreen());
        return getCurrentScreen();
    }
}