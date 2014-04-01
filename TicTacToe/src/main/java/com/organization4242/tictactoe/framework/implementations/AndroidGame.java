package com.organization4242.tictactoe.framework.implementations;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import com.organization4242.tictactoe.framework.*;

public class AndroidGame extends Activity implements Game {
	private AndroidFastRenderView renderView;
    private FileIO fileIO;
    private Screen screen;
    private PowerManager.WakeLock wakeLock;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 900 : 600;
        int frameBufferHeight = isLandscape ? 600 : 900;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX;
        float scaleY;

        if (Build.VERSION.SDK_INT < 13) {
            scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
            scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
        } else {
            Point point = new Point();
            getWindowManager().getDefaultDisplay().getSize(point);
            scaleX = point.x;
            scaleY = point.y;
        }

        renderView = new AndroidFastRenderView(this, frameBuffer);

        AndroidGraphics.getInstance().setAssets(getAssets());
        AndroidGraphics.getInstance().setFrameBuffer(frameBuffer);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AndroidAudio.getInstance().setSoundPool(new SoundPool(20, AudioManager.STREAM_MUSIC, 0));

        AndroidInput.getInstance().setAccelHandler(new AccelerometerHandler(this));
        AndroidInput.getInstance().setKeyHandler(new KeyboardHandler(renderView));
        AndroidInput.getInstance().setTouchHandler(new MultiTouchHandler(renderView, scaleX, scaleY));

        screen = getStartScreen();
        setContentView(renderView);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "GLGame");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		
		if(isFinishing()) {
            screen.dispose();
        }
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public void setScreen(Screen screen) {
		if (screen == null) {
            throw new IllegalArgumentException("Screen must not be null!");
        }

        if (this.screen != null) {
            this.screen.pause();
            this.screen.dispose();
            screen.resume();
            screen.update(0);
        }

        this.screen = screen;
	}

    @Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public Screen getStartScreen() {
        return null;
	}

}