package com.organization4242.tictactoe.framework.implementations;

import android.view.View;
import com.organization4242.tictactoe.framework.Input;

import java.util.ArrayList;
import java.util.List;

public class KeyboardHandler implements View.OnKeyListener {
    private boolean[] pressedKeys = new boolean[128];
    private Pool<Input.KeyEvent> keyEventPool;
    private List<Input.KeyEvent> keyEventBuffer = new ArrayList<Input.KeyEvent>();
    private List<Input.KeyEvent> keyEvents = new ArrayList<Input.KeyEvent>();
	
	public KeyboardHandler(View view){
		Pool.PoolObjectFactory<Input.KeyEvent> factory = new Pool.PoolObjectFactory<Input.KeyEvent>() {
			@Override
			public Input.KeyEvent createObject() {
				return new Input.KeyEvent();
			}
		};
		keyEventPool = new Pool<Input.KeyEvent>(factory, 100);
		view.setOnKeyListener(this);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
	}
	
	@Override
	public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
		if(event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE) {
            return false;
        }
		synchronized (this) {
			Input.KeyEvent keyEvent = keyEventPool.newObject();
			keyEvent.setKeyCode(keyCode);
			keyEvent.setKeyChar((char) event.getUnicodeChar());
			if(event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
				keyEvent.setType(Input.KeyEvent.KEY_DOWN);
				if (keyCode > 0 && keyCode <127) {
                    pressedKeys[keyCode] = true;
                }
			}
			if(event.getAction() == android.view.KeyEvent.ACTION_UP) {
				keyEvent.setType(Input.KeyEvent.KEY_UP);
				if (keyCode > 0 && keyCode <127) {
                    pressedKeys[keyCode] = false;
                }
			}
			keyEventBuffer.add(keyEvent);
		}
		return false;
	}
	
	public boolean isKeyPressed(int keyCode){
		if (keyCode < 0 || keyCode > 127) {
            return false;
        }
		return pressedKeys[keyCode];
	}
	public List<Input.KeyEvent> getKeyEvents(){
		synchronized (this) {
			int len = keyEvents.size();
			for (int i=0; i<len; i++) {
                keyEventPool.free(keyEvents.get(i));
            }
			keyEvents.clear();
			keyEvents.addAll(keyEventBuffer);
			keyEventBuffer.clear();
			return keyEvents;
		}
	}
}
