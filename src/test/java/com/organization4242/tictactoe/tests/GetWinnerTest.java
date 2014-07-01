package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.State;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ilya on 06.04.14.
 */
public class GetWinnerTest {
    @Test
    public void testGetWinnerRow() throws Exception {
        Field field = new Field(9);

        field.add(State.X);
        field.add(State.X);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);

        assertEquals(State.X, field.getWinner());
    }

    @Test
    public void testGetWinnerColumn() throws Exception {
        Field field = new Field(9);

        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);

        assertEquals(State.X, field.getWinner());
    }

    @Test
    public void testGetWinnerDiag() throws Exception {
        Field field = new Field(9);

        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);

        assertEquals(State.X, field.getWinner());

        field = new Field(9);

        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);

        assertEquals(State.X, field.getWinner());
    }

    @Test
    public void testGetWinnerMixed() throws Exception {
        Field field = new Field(9);

        field.add(State.O);
        field.add(State.O);
        field.add(State.O);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);

        assertEquals(State.O, field.getWinner());
    }

    @Test
    public void testGetWinnerMixed2() throws Exception {
        Field field = new Field(9);

        field.add(State.O);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);

        assertEquals(State.X, field.getWinner());
    }
}