package com.organization4242.tictactoe.app;

import android.graphics.Color;
import com.organization4242.tictactoe.framework.Game;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Screen;

import java.util.List;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeStartScreen extends Screen {
    Game game;
    private List<Byte> baseField;
    private List<Byte> fields;
    private int activeField;

    TicTacToeStartScreen(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        int cl;
        for (int i = 0; i<baseField.size(); i++) {
            switch (baseField.get(i)) {
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
            //game.getGraphics().drawRect(20*(i/180),20*(i/180));
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
}
