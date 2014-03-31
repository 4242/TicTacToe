package com.organization4242.tictactoe.framework;

public abstract class Screen {
    public Screen(Game game) {}

	public abstract void update(float deltaTime);
	
	public abstract void present(float deltaTime);
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract void dispose();
}
