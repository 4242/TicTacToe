package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.FieldInterface;
import com.organization4242.tictactoe.model.MainFieldModel;
import com.organization4242.tictactoe.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ilya on 31.03.14.
 */
public final class TicTacToeAI implements AI {
    private static final byte NONE = 10;
    public byte canWin (FieldInterface field, State order) {
        List<State> templateField = new ArrayList<State>();
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            templateField.add(field.get(i));
        }
        List<Byte> e = field.getEmptyFields();
        for (Byte index : field.getEmptyFields()) {
            FieldInterface inspectedField = new Field(MainFieldModel.NUMBER_OF_FIELDS);
            for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
                inspectedField.add(templateField.get(i));
            }
            inspectedField.set(index, order);
            if (inspectedField.getWinner() == order) {
                return index;
            }
        }
        return NONE;
    }

    public byte nextMove(FieldInterface field, State order) {
        byte winningMove = canWin(field, order);
        byte opponentWinningMove = canWin(field, State.reverse(order));
        if (winningMove != NONE) {
            return winningMove;
        } else if (opponentWinningMove != NONE) {
            return opponentWinningMove;
        }

        Random r = new Random();
        int randomPoint = r.nextInt(field.getEmptyFields().size());
        return field.getEmptyFields().get(randomPoint);
    }
}