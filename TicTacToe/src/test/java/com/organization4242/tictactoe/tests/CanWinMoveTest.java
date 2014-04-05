package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.MainFieldModel;
import com.organization4242.tictactoe.model.State;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Евгений on 03.04.2014.
 */
public class CanWinMoveTest {
    @Test
    public void canWinMoveTest() {
        TicTacToeAI ai = new TicTacToeAI(MainFieldModel.getInstance());
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

//        TicTacToeAI ai = new TicTacToeAI(MainFieldModel.getInstance());
//        List<List<Byte>> matrix = new ArrayList<List<Byte>>();
//        byte[][] m = new byte[][]{{-1,0,0},{0,0,0},{0,0,0}};
//        for (int i = 0; i <= 2; i++) {
//            matrix.add(new ArrayList<Byte>());
//            for (int j = 0; j <= 2; j++) {
//                matrix.get(i).add(m[i][j]);
//            }
//        }
//        List<List<List<Byte>>> fields = new ArrayList<List<List<Byte>>>();
//        for (int i = 0; i < Math.pow(matrix.size(), 2); i++) {
//            fields.add(matrix);
//        }
//
//        MainFieldModel state = MainFieldModel.getInstance();
//        state.setActiveField((byte) 1).setOrder(MainFieldModel.O).setFields(fields);
//        byte[] c = ai.canWin(state.getOrder());

    }
}
