package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.ai.AI;
import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.MainFieldModel;
import com.organization4242.tictactoe.model.State;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Евгений on 03.04.2014.
 */
public class NextMoveTest {
    AI ai;

    @Before
    public void setUp() throws Exception {
        MainFieldModel.getInstance().setActiveField((byte) 0);
        MainFieldModel.getInstance().setOrder(State.X);
        ai = new TicTacToeAI(MainFieldModel.getInstance());
    }

    @Test
    public void nextMoveTestRow() {
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
        MainFieldModel.getInstance().getFields().set(0, field);
        assertEquals(2, ai.nextMove());
    }

    @Test
    public void nextMoveTestColumn() {
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
        MainFieldModel.getInstance().getFields().set(0, field);
        assertEquals(6, ai.nextMove());
    }

    @Test
    public void nextMoveTestDiag() {
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
        MainFieldModel.getInstance().getFields().set(0, field);
        assertEquals(4, ai.nextMove());
    }

    @Test
    public void nextMoveTestDiag2() {
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
        MainFieldModel.getInstance().getFields().set(0, field);
        assertEquals(8, ai.nextMove());
    }

    @Test
    public void nextMoveTestDiag3() {
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
        MainFieldModel.getInstance().getFields().set(0, field);
        assertEquals(0, ai.nextMove());
    }
}