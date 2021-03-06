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

    private MainFieldModel state;
    private byte activeField;

    public byte getActiveField() {
        return activeField;
    }

    public TicTacToeAI(MainFieldModel state) {
        this.state = state;
        Random r = new Random();
        if (this.state.getActiveField() == MainFieldModel.ANY) {
            activeField = this.state.getBaseField().getAvailableFields()
                    .get(r.nextInt(state.getBaseField().getAvailableFields().size()));
        } else {
            activeField = this.state.getActiveField();
        }
    }

    public byte canWin (FieldInterface field, State order) {
        List<State> templateField = new ArrayList<State>();
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            templateField.add(field.get(i));
        }
        for (Byte index : field.getEmptyPoints()) {
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

    public List<Byte> getAvailablePoints() {
        List<Byte> emptyFields = new ArrayList<Byte>();
        State order = state.getOrder();
        FieldInterface currentField = state.getFields().get(activeField);
        for (int i = 0; i < currentField.size(); i++) {
            if (currentField.get(i) == State.EMPTY && state.canMove(activeField, (byte) i, order)) {
                emptyFields.add((byte) i);
            }
        }
        return emptyFields;
    }

    public byte nextMove() {
        Random r = new Random();
        FieldInterface field = state.getFields().get(activeField);
        State order = state.getOrder();

        byte winningMove = canWin(field, order);
        byte opponentWinningMove = canWin(field, State.reverse(order));
        if (winningMove != NONE && state.canMove(activeField, winningMove, order)) {
            return winningMove;
        } else if (opponentWinningMove != NONE && state.canMove(activeField, opponentWinningMove, order)) {
            return opponentWinningMove;
        }

        if (field.getEmptyPoints().size() == 1) {
            return field.getEmptyPoints().get(0);
        }

        List<Byte> availablePoints = getAvailablePoints();
        return availablePoints.get(r.nextInt(availablePoints.size()));
    }
}