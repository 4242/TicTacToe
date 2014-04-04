package com.organization4242.tictactoe.app;

import com.organization4242.tictactoe.ai.AI;

/**
 * Created by ilya on 01.04.14.
 */
public class Controller extends AbstractController {
    public static final String MODEL_UPDATED = "ModelUpdated";
    public static final String VIEW_UPDATED = "ViewUpdated";

    private AI ai;

    public void setAi(AI ai) {
        this.ai = ai;
    }
}
