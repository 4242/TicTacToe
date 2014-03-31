package com.organization4242.tictactoe.model;

import java.util.List;

/**
 * Created by Евгений on 31.03.2014.
 */
public class CheckForWin {
    public static boolean IsWin (List<List<Integer>> matrix, boolean order) {
        int sums[] = {0,0,0,0,0,0,0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sums[i]+=matrix.get(i).get(j);
                sums[i+3]+=matrix.get(j).get(i);
            }
            sums[6]+=matrix.get(i).get(i);
            sums[7]+=matrix.get(i).get(3-i);
        }
        for (int i = 0; i < 8; i++) {
            if( order && sums[i]== 3) return true;
            if(!order && sums[i]==-3) return true;
        }
        return false;
    }
}
