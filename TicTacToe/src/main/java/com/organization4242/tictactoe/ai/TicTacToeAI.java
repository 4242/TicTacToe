package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.CheckForWin;
import com.organization4242.tictactoe.model.MainField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public class TicTacToeAI {
    public static Byte[] nextMove(MainField state) {
        int activeField = state.getActiveField();
        boolean order = state.getOrder();
        Byte[] move = new Byte[]{0,0};
        List<List<Integer>> templateField = new ArrayList<List<Integer>>();
        List<List<Integer>> inspectedField = new ArrayList<List<Integer>>();
        templateField.addAll(state.getFields().get(activeField));
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                inspectedField.addAll(templateField);
                if(state.getFields().get(activeField).get(i).get(j) == 0) {
                    inspectedField.get(i).set(j, 1);
                    if(CheckForWin.Winner(inspectedField) == 1) {
                        move = new Byte[]{(byte) i,(byte) j};
                        return move;
                    }
                }
            }
        }
        return move;

    }
}
