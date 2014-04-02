package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.MainField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public final class TicTacToeAI {
    private TicTacToeAI() {}

    public static Byte[] nextMove(MainField state) {
        byte activeField = state.getActiveField();
        boolean order = state.getOrder();
        Byte[] move = new Byte[]{0,0};
        List<List<Byte>> templateField = new ArrayList<List<Byte>>();
        List<List<Byte>> inspectedField = new ArrayList<List<Byte>>();
        templateField.addAll(state.getFields().get(activeField));
        for (int i = 0; i < MainField.NUMBER_OF_FIELDS; i++) {
            for(int j = 0; j < MainField.NUMBER_OF_FIELDS; j++) {
                inspectedField.addAll(templateField);
                if(state.getFields().get(activeField).get(i).get(j).equals(MainField.EMPTY)) {
                    inspectedField.get(i).set((byte) j, MainField.X);
                    if(MainField.winner(inspectedField).equals(MainField.X)) {
                        move = new Byte[]{(byte) i,(byte) j};
                        return move;
                    }
                }
            }
        }
        return move;

    }
}
