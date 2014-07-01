package com.organization4242.tictactoe.framework.implementations;

import android.content.res.AssetManager;
import android.os.Environment;
import com.organization4242.tictactoe.framework.FileIO;

import java.io.*;

public final class AndroidFileIO implements FileIO{
    private AssetManager assets;
    private String externalStoragePath;
    private static AndroidFileIO instance = new AndroidFileIO();

    public void setAssets(AssetManager assets) {
        this.assets = assets;
        this.externalStoragePath =
                Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public static AndroidFileIO getInstance() {
        return instance;
    }

    private AndroidFileIO() {}
	
	@Override
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}
	
	@Override
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(externalStoragePath + fileName);
	}
	
	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}
	
	
}
