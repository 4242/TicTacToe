package com.organization4242.tictactoe.framework.implementations;

import android.view.View;
import com.organization4242.tictactoe.framework.Input;

import java.util.List;

public interface TouchHandler extends View.OnTouchListener {
	public boolean isTouchDown(int pointer);
	
	public int getTouchX(int pointer);
	
	public int getTouchY(int pointer);
	
	public List<Input.TouchEvent> getTouchEvents();
}
