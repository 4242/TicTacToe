package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.MainField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by ilya on 31.03.14.
 */
public final class TicTacToeAI implements AI {

    MainField state;

    private byte[] canWin (Byte order) {
        byte[] move;
        byte activeField = state.getActiveField();
        List<List<Byte>> templateField = new ArrayList<List<Byte>>();
        List<List<Byte>> inspectedField = new ArrayList<List<Byte>>();
        templateField.addAll(state.getFields().get(activeField));
        for (int i = 0; i < MainField.NUMBER_OF_FIELDS; i++) {
            for (int j = 0; j < MainField.NUMBER_OF_FIELDS; j++) {
                inspectedField.addAll(templateField);
                if (state.getFields().get(activeField).get(i).get(j).equals(MainField.EMPTY)) {
                    inspectedField.get(i).set((byte) j, order);
                    if (MainField.winner(inspectedField).equals(order)) {
                        move = new byte[]{(byte) i, (byte) j};
                        return move;
                    }
                }
            }
        }
        return null;
    }

    public byte[] nextMove(MainField state) {
        this.state = state;
        byte activeField = state.getActiveField();
        Byte order = state.getOrder();

        byte[] move;
        move = canWin(order);
        if (move != null) {
            return move;
        }

        move = canWin((byte) (-1*order));
        if (move != null) {
            return move;
        }

        return move;
    }
}
