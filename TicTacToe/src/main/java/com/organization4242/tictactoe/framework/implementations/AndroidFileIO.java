package com.organization4242.tictactoe.framework.implementations;

import android.content.res.AssetManager;
import android.os.Environment;
import com.organization4242.tictactoe.framework.FileIO;

import java.io.*;

public class AndroidFileIO implements FileIO{
    private AssetManager assets;
    private String externalStoragePath;
	
	public AndroidFileIO(AssetManager assets) {
		this.assets = assets;
		StringBuilder strbuilder = new StringBuilder();
		strbuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		strbuilder.append(File.separator);
		this.externalStoragePath = strbuilder.toString();
		//		+ File.separator; // ������� �� ��� ���������� ����� ������������
	}
	
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
