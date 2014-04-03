package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.MainField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ilya on 31.03.14.
 */
public final class TicTacToeAI implements AI {

    MainField state;

    public TicTacToeAI(MainField state) {
        this.state = state;
    }

    public byte[] canWin (Byte order) {
        byte[] move;
        byte activeField = state.getActiveField();

        List<List<Byte>> templateField = new ArrayList<List<Byte>>();
        List<List<Byte>> inspectedField = new ArrayList<List<Byte>>();
        List<List<Byte>> actField = state.getFields().get(activeField);
        for (int i = 0; i < actField.size(); i++) {
            List<Byte> l = actField.get(i);
            templateField.add(new ArrayList<Byte>());
            inspectedField.add(new ArrayList<Byte>());
            for (Byte b : l) {
                templateField.get(i).add(b);
                inspectedField.get(i).add(b);
            }
        }
        for (int i = 0; i < MainField.NUMBER_OF_FIELD; i++) {
            for (int j = 0; j < MainField.NUMBER_OF_FIELD; j++) {
                if (templateField.get(i).get(j).equals(MainField.EMPTY)) {
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

    public byte[] nextMove() {
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
        Random r = new Random();
        int randomPoint = r.nextInt(state.freePoints().size());
        move = state.freePoints().get(randomPoint);
        return move;
    }
}
