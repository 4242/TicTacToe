package com.organization4242.tictactoe.ai;

import com.organization4242.tictactoe.model.MainField;

/**
 * Created by ilya on 03.04.14.
 */
public interface AI {
    Byte[] nextMove(MainField state);
}
