package com.organization4242.tictactoe.controller;

import com.organization4242.tictactoe.model.MainField;
import com.organization4242.tictactoe.view.AbstractView;

import java.beans.PropertyChangeEvent;

/**
 * Created by ilya on 01.04.14.
 */
public class Controller extends AbstractController {
    public static final String MODEL_UPDATED = "ModelUpdated";
    public static final String VIEW_UPDATED = "ViewUpdated";
    public static final String DISPOSING = "Disposing";

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getSource() instanceof AbstractView) {
            if (pce.getPropertyName().equals(MODEL_UPDATED)) {
                byte[] move = (byte[]) pce.getNewValue();
                if (MainField.getInstance().canMove(move[0], move[1])) {
                    super.propertyChange(pce);
                }
            } else if (pce.getPropertyName().equals(DISPOSING)) {
                super.propertyChange(pce);
            }
        }
    }
}
