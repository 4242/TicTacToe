package com.organization4242.tictactoe.framework;

import com.organization4242.tictactoe.view.AbstractView;

import java.util.Observer;

public abstract class Screen extends AbstractView implements Observer {
    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
