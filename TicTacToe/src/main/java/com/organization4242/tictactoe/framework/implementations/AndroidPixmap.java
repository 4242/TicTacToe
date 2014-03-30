package com.organization4242.tictactoe.framework.implementations;

import android.graphics.Bitmap;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
    private Bitmap bitmap;
    private Graphics.PixmapFormat format;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format)
	{
		this.bitmap = bitmap;
		this.format = format;
	}

	@Override
	public int getWidth(){
		return bitmap.getWidth();
	}

	@Override
	public int getHeight(){
		return bitmap.getHeight();
	}
	
	@Override
	public Graphics.PixmapFormat getFormat(){
		return format;
	}
	
	@Override
	public void dispose(){
		bitmap.recycle();
	}
}
