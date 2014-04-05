package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.State;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Евгений on 03.04.2014.
 */
public class CanWinMoveTest {
    @Test
    public void canWinMoveTestRow() {
        TicTacToeAI ai = new TicTacToeAI();
        Field field = new Field(9);
        field.add(State.X);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        assertEquals(2, ai.canWin(field, State.X));
    }

    @Test
    public void canWinMoveTestColumn() {
        TicTacToeAI ai = new TicTacToeAI();
        Field field = new Field(9);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        assertEquals(6, ai.canWin(field, State.X));
    }

    @Test
    public void canWinMoveTestDiag() {
        TicTacToeAI ai = new TicTacToeAI();
        Field field = new Field(9);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        assertEquals(4, ai.canWin(field, State.X));
    }
}