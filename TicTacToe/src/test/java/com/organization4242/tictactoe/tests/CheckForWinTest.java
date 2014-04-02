package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.model.MainField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Евгений on 31.03.2014.
 */
@RunWith(JUnit4.class)
public class CheckForWinTest {

    @Test
    public void testWinner() throws Exception {
        List<List<Byte>> matrix = new ArrayList<List<Byte>>();
        byte[][] m = new byte[][]{{-1,-1,-1},{0,0,0},{0,0,0}};
        for (int i = 0; i <= 2; i++) {
            matrix.add(new ArrayList<Byte>());
            for (int j = 0; j <= 2; j++) {
                matrix.get(i).add(m[i][j]);
            }
        }
        assertEquals((byte) MainField.winner(matrix), (byte) -1);
    }
}
