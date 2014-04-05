package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.FieldInterface;
import com.organization4242.tictactoe.model.State;

/**
 * Created by ilya on 03.04.14.
 */
public interface AI {
    byte nextMove(FieldInterface field, State orde);
}
