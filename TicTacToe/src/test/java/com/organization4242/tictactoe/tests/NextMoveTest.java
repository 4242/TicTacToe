package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.State;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Евгений on 03.04.2014.
 */
public class NextMoveTest {
    @Test
    public void nextMoveTestRow() {
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
        assertEquals(2, ai.nextMove(field, State.X));
    }

    @Test
    public void nextMoveTestColumn() {
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
        assertEquals(6, ai.nextMove(field, State.X));
    }

    @Test
    public void nextMoveTestDiag() {
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
        assertEquals(4, ai.nextMove(field, State.X));
    }

    @Test
    public void nextMoveTestDiag2() {
        TicTacToeAI ai = new TicTacToeAI();
        Field field = new Field(9);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        assertEquals(8, ai.nextMove(field, State.X));
    }

    @Test
    public void nextMoveTestDiag3() {
        TicTacToeAI ai = new TicTacToeAI();
        Field field = new Field(9);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        assertEquals(0, ai.nextMove(field, State.X));
    }
}