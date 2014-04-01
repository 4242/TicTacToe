package com.organization4242.tictactoe.framework;

public interface Game {
	//public Input getInput();

	//public Graphics getGraphics();
	
	public FileIO getFileIO();

	//public Audio getAudio();

	public void setScreen(Screen screen);

	public Screen getCurrentScreen();

	public Screen getStartScreen();
}
