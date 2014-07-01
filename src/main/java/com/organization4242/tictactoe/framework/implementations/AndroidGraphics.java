package com.organization4242.tictactoe.framework.implementations;

import android.content.res.AssetManager;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Paint.Style;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Pixmap;

import java.io.IOException;
import java.io.InputStream;

public final class AndroidGraphics implements Graphics {
    private AssetManager assets;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();

    private static AndroidGraphics graphics = new AndroidGraphics();

    public static AndroidGraphics getInstance() {
        return graphics;
    }

    public void setAssets(AssetManager assets) {
        this.assets = assets;
    }

    public void setFrameBuffer(Bitmap frameBuffer) {
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(this.frameBuffer);
        this.paint = new Paint();
    }

    private AndroidGraphics(){

	}
	
	@Override
	public Pixmap newPixmap(String fileName, PixmapFormat format) throws IOException {
		Config config;
        PixmapFormat newFormat;
		if (format == PixmapFormat.RGB565) {
            config = Config.RGB_565;
        } else if (format == PixmapFormat.ARGB4444) {
            config = Config.ARGB_4444;
        } else {
            config = Config.ARGB_8888;
        }
		
		Options options = new Options();
		options.inPreferredConfig = config;
		
		InputStream in = null;
		Bitmap bitmap = null;
		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in);
			if (bitmap == null) {
                throw new IOException(String.format("Couldn't load bitmap from asset '%s'", fileName));
            }
		}
		finally {
			if (in != null) {
                in.close();
			}
		}

		if (bitmap.getConfig() == Config.RGB_565) {
            newFormat = PixmapFormat.RGB565;
        } else if (bitmap.getConfig() == Config.ARGB_4444) {
            newFormat = PixmapFormat.ARGB4444;
        } else {
            newFormat = PixmapFormat.ARGB888;
        }

		return new AndroidPixmap(bitmap, newFormat);
	}

	@Override
	public void clear(int color) {
		canvas.drawRGB((color & 0xFF0000) >> 16, (color & 0xFF00) >> 8, (color & 0xFF));		
	}

	@Override
	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x+width-1, y+height-1, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		srcRect.left=x;
		srcRect.top=y;
		srcRect.right=x + srcWidth - 1;
		srcRect.bottom=y + srcHeight - 1;
		
		dstRect.left=x;
		dstRect.top=y;
		dstRect.right=x + srcWidth - 1;
		dstRect.bottom=y + srcHeight - 1;
		canvas.drawBitmap(((AndroidPixmap) pixmap).getBitmap(), srcRect, dstRect, null);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y) {
		canvas.drawBitmap(((AndroidPixmap) pixmap).getBitmap(), x, y, null);
	}

	@Override
	public int getHeight() {
		return frameBuffer.getHeight();
	}

	@Override
	public int getWidth() {
		return frameBuffer.getWidth();
	}
	
}