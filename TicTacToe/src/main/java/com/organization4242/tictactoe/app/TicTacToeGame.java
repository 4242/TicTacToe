package com.organization4242.tictactoe.app;

import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGame;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new TicTacToeStartScreen(this);
    }
}