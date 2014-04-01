package com.organization4242.tictactoe.framework.implementations;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import com.organization4242.tictactoe.framework.Audio;
import com.organization4242.tictactoe.framework.Music;
import com.organization4242.tictactoe.framework.Sound;

import java.io.IOException;

public class AndroidAudio implements Audio {
	private AssetManager assets;
    private SoundPool soundPool;

    public void setAssets(AssetManager assets) {
        this.assets = assets;
    }

    public void setSoundPool(SoundPool soundPool) {
        this.soundPool = soundPool;
    }

    private static AndroidAudio audio = new AndroidAudio();

	private AndroidAudio() {

	}

    public static AndroidAudio getInstance() {
        return audio;
    }
	
	@Override
	public Music newMusic(String filename) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			return new AndroidMusic(assetDescriptor);
		} catch (IOException e) {
			throw new RuntimeException("������ ���������� ���� : "+ filename + "");
		}
	}
	
	@Override
	public Sound newSound(String filename) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} catch (IOException e) {
			throw new RuntimeException("������ ���������� ���� : "+ filename + "");
		}
	}
}
