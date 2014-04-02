package com.organization4242.tictactoe.framework.implementations;

import com.organization4242.tictactoe.framework.Input;

import java.util.List;

public class AndroidInput implements Input {
    private AccelerometerHandler accelHandler;
    private KeyboardHandler keyHandler;
    private TouchHandler touchHandler;

    public void setAccelHandler(AccelerometerHandler accelHandler) {
        this.accelHandler = accelHandler;
    }

    public void setKeyHandler(KeyboardHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public void setTouchHandler(TouchHandler touchHandler) {
        this.touchHandler = touchHandler;
    }

    private AndroidInput() {

    }

    private static AndroidInput instance = new AndroidInput();

    public static AndroidInput getInstance() {
        return instance;
    }

	@Override
	public boolean isKeyPressed(int keyCode) {
		return keyHandler.isKeyPressed(keyCode);
	}

	@Override
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouchX(int pointer) {
		return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer) {
		return touchHandler.getTouchY(pointer);
	}
	
	@Override
	public float getAccelX(){
		return accelHandler.getAccelX();
	}
	
	@Override
	public float getAccelY(){
		return accelHandler.getAccelY();
	}
	
	@Override
	public float getAccelZ(){
		return accelHandler.getAccelZ();
	}
	
	@Override
	public List<TouchEvent> getTouchEvents(){
		return touchHandler.getTouchEvents();
	}
	
	@Override
	public List<KeyEvent> getKeyEvents(){
		return keyHandler.getKeyEvents();
	}
}
