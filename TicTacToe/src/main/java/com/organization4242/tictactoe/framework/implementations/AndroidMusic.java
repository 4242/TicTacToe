package com.organization4242.tictactoe.framework.implementations;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.organization4242.tictactoe.framework.Music;

import java.io.IOException;

public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer;
    private boolean isPrepared = false;
	
	public AndroidMusic(AssetFileDescriptor assetDescriptor) throws IOException {
		mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                assetDescriptor.getStartOffset(),
                assetDescriptor.getLength());
        mediaPlayer.prepare();
        isPrepared = true;
        mediaPlayer.setOnCompletionListener(this);
	}
	
	@Override
	public void dispose() {
		if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
		mediaPlayer.release();
	}

	@Override
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}
	
	@Override
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStopped() {
		return !isPrepared;
	}

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }
    
	@Override
	public void play() throws IOException {
		if (mediaPlayer.isPlaying()) {
            return;
        }
        synchronized (this) {
            if (!isPrepared) {
                mediaPlayer.prepare();
            }
            mediaPlayer.start();
        }
	}
	
	@Override
	public void setLooping(boolean isLooping) {
		mediaPlayer.setLooping(isLooping);
	}
	
	@Override
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
	}

	@Override
	public void stop(){
		mediaPlayer.stop();
		synchronized (this) {
			isPrepared = false;
		}
	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer){
		synchronized (this) {
			isPrepared = false;
		}
	}
}
