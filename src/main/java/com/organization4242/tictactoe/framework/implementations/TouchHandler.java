package com.organization4242.tictactoe.framework.implementations;

import android.view.View;
import com.organization4242.tictactoe.framework.Input;

import java.util.List;

public interface TouchHandler extends View.OnTouchListener {
	boolean isTouchDown(int pointer);
	
	int getTouchX(int pointer);
	
	int getTouchY(int pointer);
	
	List<Input.TouchEvent> getTouchEvents();
}
