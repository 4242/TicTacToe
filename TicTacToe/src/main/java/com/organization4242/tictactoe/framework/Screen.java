package com.organization4242.tictactoe.framework;

import com.organization4242.tictactoe.view.AbstractView;

public abstract class Screen extends AbstractView {
    public Screen(Game game) {}

	public abstract void update(float deltaTime);
	
	public abstract void present(float deltaTime);
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void dispose();
}
