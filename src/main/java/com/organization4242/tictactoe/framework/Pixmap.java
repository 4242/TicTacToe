package com.organization4242.tictactoe.framework;

public interface Pixmap {
    int getWidth();
	
    int getHeight();
	
    Graphics.PixmapFormat getFormat();
	
    void dispose();
}
