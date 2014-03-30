package com.organization4242.tictactoe.framework.implementations;

import android.view.MotionEvent;
import android.view.View;
import com.organization4242.tictactoe.framework.Input;

import java.util.ArrayList;
import java.util.List;

public class SingleTouchHandler implements TouchHandler{ //��� ������ ������ Android
    private boolean isTouched;
    private int touchX;
    private int touchY;
    private Pool<Input.TouchEvent> touchEventPool;
    private List<Input.TouchEvent> touchEvents = new ArrayList<Input.TouchEvent>();
    private List<Input.TouchEvent> touchEventsBuffer = new ArrayList<Input.TouchEvent>();
    private float scaleX;
    private float scaleY;
	
	public SingleTouchHandler(View view, float scaleX, float scaleY){
		Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
			@Override
			public Input.TouchEvent createObject() {
				return new Input.TouchEvent();
			}
		};
		touchEventPool = new Pool<Input.TouchEvent>(factory, 100);
		view.setOnTouchListener(this);
		
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event){
		synchronized(this) {
			Input.TouchEvent touchEvent = touchEventPool.newObject();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				touchEvent.setType(Input.TouchEvent.TOUCH_DOWN);
				isTouched = true;
				break;
			case MotionEvent.ACTION_MOVE:
				touchEvent.setType(Input.TouchEvent.TOUCH_DRAGGED);
				isTouched = true;
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				touchEvent.setType(Input.TouchEvent.TOUCH_UP);
				isTouched = false;
				break;
			}

            touchX = (int) (event.getX()*scaleX);
			touchEvent.setX(touchX);
            touchY = (int) (event.getY()*scaleY);
			touchEvent.setY(touchY);
			touchEventsBuffer.add(touchEvent);
			
			return true;
		}
	}
	
	@Override
	public boolean isTouchDown(int pointer){
		synchronized(this){
			if(pointer == 0) {
                return isTouched;
            } else {
                return false;
            }
		}
	}
	
	@Override
	public int getTouchX(int pointer){
		synchronized(this){
			return touchX;
		}
	}
	
	@Override
	public int getTouchY(int pointer){
		synchronized(this){
			return touchY;
		}
	}
	
	@Override
	public List<Input.TouchEvent> getTouchEvents(){
		synchronized(this){
			for (Input.TouchEvent touchEvent : touchEvents) {
                touchEventPool.free(touchEvent);
            }
			touchEvents.clear();
			touchEvents.addAll(touchEventsBuffer);
			touchEventsBuffer.clear();
			return touchEvents;
		}
	}
}