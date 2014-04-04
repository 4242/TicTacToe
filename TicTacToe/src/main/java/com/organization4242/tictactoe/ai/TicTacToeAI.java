package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.MainField;
import com.organization4242.tictactoe.model.State;

/**
 * Created by ilya on 31.03.14.
 */
public final class TicTacToeAI implements AI {

    MainField state;

    public TicTacToeAI(MainField state) {
        this.state = state;
    }

    public byte[] canWin (State order) {
//        byte[] move;
//        byte activeField = state.getActiveField();
//
//        List<List<Byte>> templateField = new ArrayList<List<Byte>>();
//
//        List<List<Byte>> actField = state.getFields().get(activeField);
//        for (int i = 0; i < actField.size(); i++) {
//            List<Byte> l = actField.get(i);
//            templateField.add(new ArrayList<Byte>());
//            for (Byte b : l) {
//                templateField.get(i).add(new Byte(b));
//            }
//        }
//        for (int i = 0; i < MainField.NUMBER_OF_FIELDS; i++) {
//            for (int j = 0; j < MainField.NUMBER_OF_FIELDS; j++) {
//                if (templateField.get(i).get(j).equals(State.EMPTY)) {
//                    List<List<Byte>> inspectedField = new ArrayList<List<Byte>>();
//                    for (int k = 0; k < actField.size(); k++) {
//                        List<Byte> l = actField.get(k);
//                        inspectedField.add(new ArrayList<Byte>());
//                        for (Byte b : l) {
//                            inspectedField.get(k).add(new Byte(b));
//                        }
//                    }
//                    inspectedField.get(i).set((byte) j, order);
//                    if (MainField.winner(inspectedField).equals(order)) {
//                        move = new byte[]{(byte) i, (byte) j};
//                        return move;
//                    }
//                }
//            }
//        }
        return null;
    }

    public byte[] nextMove() {
//        byte activeField = state.getActiveField();
//        State order = state.getOrder();
//
//        byte[] move;
//        move = canWin(order);
//        if (move != null) {
//            return move;
//        }
//
//        move = canWin((byte) (-1*order));
//        if (move != null) {
//            return move;
//        }
//        Random r = new Random();
//        int randomPoint = r.nextInt(state.freePoints().size());
//        move = state.freePoints().get(randomPoint);
//        return move;
        return null;
    }
}
