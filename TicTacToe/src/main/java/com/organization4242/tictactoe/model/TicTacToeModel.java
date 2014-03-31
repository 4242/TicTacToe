package com.organization4242.tictactoe.model;

import android.util.Log;
import com.organization4242.tictactoe.app.TicTacToeController;

import java.beans.PropertyChangeEvent;
import java.util.Arrays;

/**
 * Created by ilya on 31.03.14.
 */
public class TicTacToeModel extends AbstractModel {
    private MainField mainField = new MainField();

    @Override
    public void viewPropertyChange(PropertyChangeEvent pce) {
        Log.d(TicTacToeController.VIEW_UPDATED, Arrays.toString(((Object[]) pce.getNewValue())));
    }
}
