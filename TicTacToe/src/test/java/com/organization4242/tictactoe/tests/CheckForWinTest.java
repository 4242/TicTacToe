package com.organization4242.tictactoe.tests;

import com.organization4242.tictactoe.model.CheckForWin;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Евгений on 31.03.2014.
 */
@RunWith(JUnit4.class)
public class CheckForWinTest {

    @Test
    public void testIsWin() throws Exception {
        List<List<Integer>> matrix = new ArrayList<List<Integer>>();
        int[][] m = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        for (int i = 0; i < 3; i++) {
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < 3; j++) {
                matrix.get(i).add(m[i][j]);
            }
        }
        CheckForWin.IsWin(matrix);
    }
}
