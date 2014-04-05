package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.State;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ilya on 06.04.14.
 */
public class GetEmptyFieldsTest {
    @Test
    public void testGetEmptyFields() throws Exception {
        Field field = new Field(9);

        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.X);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);

        ArrayList<Byte> expected = new ArrayList<Byte>();
        expected.add((byte) 1);
        expected.add((byte) 2);
        expected.add((byte) 3);
        expected.add((byte) 4);
        expected.add((byte) 6);
        expected.add((byte) 7);
        expected.add((byte) 8);

        assertEquals(expected, field.getEmptyFields());
    }

    @Test
    public void testGetEmptyFieldsWhenEveryFieldIsEmpty() throws Exception {
        Field field = new Field(9);

        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);
        field.add(State.EMPTY);

        ArrayList<Byte> expected = new ArrayList<Byte>();
        expected.add((byte) 0);
        expected.add((byte) 1);
        expected.add((byte) 2);
        expected.add((byte) 3);
        expected.add((byte) 4);
        expected.add((byte) 5);
        expected.add((byte) 6);
        expected.add((byte) 7);
        expected.add((byte) 8);

        assertEquals(expected, field.getEmptyFields());
    }

    @Test
    public void testGetEmptyFieldsWhenEveryFieldIsFilled() throws Exception {
        Field field = new Field(9);

        field.add(State.X);
        field.add(State.X);
        field.add(State.X);
        field.add(State.X);
        field.add(State.X);
        field.add(State.X);
        field.add(State.X);
        field.add(State.X);
        field.add(State.X);

        ArrayList<Byte> expected = new ArrayList<Byte>();

        assertEquals(expected, field.getEmptyFields());
    }
}
