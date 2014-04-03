package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.ai.AI;
import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.model.MainField;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 03.04.2014.
 */
public class NextMoveTest {
    @Test
    public void nextMoveTest() {
        AI ai = new TicTacToeAI();
        List<List<Byte>> matrix = new ArrayList<List<Byte>>();
        byte[][] m = new byte[][]{{-1,-1,0},{0,0,0},{0,0,0}};
        for (int i = 0; i <= 2; i++) {
            matrix.add(new ArrayList<Byte>());
            for (int j = 0; j <= 2; j++) {
                matrix.get(i).add(m[i][j]);
            }
        }
        List<List<List<Byte>>> fields = new ArrayList<List<List<Byte>>>();
        for (int i = 0; i < Math.pow(matrix.size(), 2); i++) {
            fields.add(matrix);
        }

        MainField state = MainField.getInstance();
        state.setActiveField((byte) 1).setOrder(MainField.O).setFields(fields);
        byte[] b = ai.nextMove(state);
    }
}
