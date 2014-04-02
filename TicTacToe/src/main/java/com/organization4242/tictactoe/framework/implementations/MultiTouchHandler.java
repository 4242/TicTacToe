package com.organization4242.tictactoe.framework.implementations;

import android.view.MotionEvent;
import android.view.View;
import com.organization4242.tictactoe.framework.Input;

import java.util.ArrayList;
import java.util.List;

public class MultiTouchHandler implements TouchHandler{
    private boolean[] isTouched = new boolean[20];
    private int[] touchX = new int[20];
    private int[] touchY = new int[20];
    private Pool<Input.TouchEvent> touchEventPool;

    private List<Input.TouchEvent> touchEvents = new ArrayList<Input.TouchEvent>();
    private List<Input.TouchEvent> touchEventsBuffer = new ArrayList<Input.TouchEvent>();
    private float scaleX;
    private float scaleY;
	
	public MultiTouchHandler(View view, float scaleX, float scaleY){
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
			int action = event.getAction() & MotionEvent.ACTION_MASK;
			int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
					>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			int pointerId = event.getPointerId(pointerIndex);
			Input.TouchEvent touchEvent;
			
			switch (action) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				touchEvent = touchEventPool.newObject();
				touchEvent.setType(Input.TouchEvent.TOUCH_DOWN);
				touchEvent.setPointer(pointerId);
                touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.setX(touchX[pointerId]);
                touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
                touchEvent.setY(touchY[pointerId]);
				isTouched[pointerId] = true;
				touchEventsBuffer.add(touchEvent);
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				touchEvent = touchEventPool.newObject();
                touchEvent.setType(Input.TouchEvent.TOUCH_UP);
                touchEvent.setPointer(pointerId);
                touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.setX(touchX[pointerId]);
                touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
                touchEvent.setY(touchY[pointerId]);
				isTouched[pointerId] = false;
				touchEventsBuffer.add(touchEvent);
				break;
			case MotionEvent.ACTION_MOVE:
				int pointerCount = event.getPointerCount();
				for(int i = 0; i < pointerCount; i++) {
					pointerIndex = i;
					pointerId = event.getPointerId(pointerIndex);

					touchEvent = touchEventPool.newObject();
                    touchEvent.setType(Input.TouchEvent.TOUCH_DRAGGED);
                    touchEvent.setPointer(pointerId);
                    touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
                    touchEvent.setX(touchX[pointerId]);
                    touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
                    touchEvent.setY(touchY[pointerId]);
				}
				break;
			}
			return true;
		}
	}
		
		@Override
		public boolean isTouchDown(int pointer) {
			synchronized (this) {
				if (pointer < 0 || pointer >=20) {
                    return false;
                }
				else {
                    return isTouched[pointer];
                }
			}
		}

		@Override
		public int getTouchX(int pointer){
			synchronized(this){
				if (pointer < 0 || pointer >=20) {
                    return 0;
                } else {
                    return touchX[pointer];
                }
			}
		}
		
		@Override
		public int getTouchY(int pointer){
			synchronized(this){
				if (pointer < 0 || pointer >=20) {
                    return 0;
                } else {
                    return touchY[pointer];
                }
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
