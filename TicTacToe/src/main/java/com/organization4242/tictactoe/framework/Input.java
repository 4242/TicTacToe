package com.organization4242.tictactoe.framework;

import java.util.List;

public interface Input {
    static class KeyEvent {
		public static final int KEY_DOWN=0;
		public static final int KEY_UP=1;
		
		private int type;
		private int keyCode;
		private char keyChar;

        public int getType() {
            return type;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public char getKeyChar() {
            return keyChar;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setKeyCode(int keyCode) {
            this.keyCode = keyCode;
        }

        public void setKeyChar(char keyChar) {
            this.keyChar = keyChar;
        }
    }
	
    static class TouchEvent {
		public static final int TOUCH_DOWN=0;
		public static final int TOUCH_UP=1;
		public static final int TOUCH_DRAGGED=2;
		
		private int type;
		private int x;
        private int y;
		private int pointer;

        public int getType() {
            return type;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getPointer() {
            return pointer;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setPointer(int pointer) {
            this.pointer = pointer;
        }
    }
	
    boolean isKeyPressed(int keyCode);
	
    boolean isTouchDown(int pointer);
	
    int getTouchX(int pointer);
	
    int getTouchY(int pointer);
	
    float getAccelX();
	
    float getAccelY();
	
    float getAccelZ();
	
    List<KeyEvent> getKeyEvents();
	
    List<TouchEvent> getTouchEvents();
}
