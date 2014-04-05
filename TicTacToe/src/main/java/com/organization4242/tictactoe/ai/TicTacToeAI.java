package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.Field;
import com.organization4242.tictactoe.model.FieldInterface;
import com.organization4242.tictactoe.model.MainFieldModel;
import com.organization4242.tictactoe.model.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public final class TicTacToeAI implements AI {
    MainFieldModel state;

    public TicTacToeAI(MainFieldModel state) {
        this.state = state;
    }

    public byte canWin (FieldInterface field, State order) {
        byte move = 0;

        List<State> templateField = new ArrayList<State>();
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            templateField.add(field.get(i));
        }
        for (Byte index : field.getEmptyFields()) {
            FieldInterface inspectedField = new Field(MainFieldModel.NUMBER_OF_FIELDS);
            for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
                inspectedField.add(templateField.get(i));
            }
            inspectedField.set(index, order);
            if (inspectedField.getWinner() == order) {
                move = index;
                return move;
            }
        }
        return move;
    }

    public byte nextMove() {
        byte activeField = state.getActiveField();
        State order = state.getOrder();

//        Random r = new Random();
//        int randomPoint = r.nextInt(state.freePoints().size());
//        move = state.freePoints().get(randomPoint);
//        return move;
        return 0;
    }
}
