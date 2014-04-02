package com.organization4242.tictactoe.framework;

import java.io.IOException;

public interface Music {
    void play() throws IOException;
	
    void stop();
	
    void pause();
	
    void setLooping(boolean looping);

    void setVolume(float volume);

    boolean isPlaying();
	
    boolean isStopped();
	
    boolean isLooping();
	
    void dispose();
}
