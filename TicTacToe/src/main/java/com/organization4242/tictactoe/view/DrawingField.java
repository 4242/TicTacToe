package com.organization4242.tictactoe.view;

import android.graphics.Color;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;

/**
 * Created by ilya on 04.04.14.
 */
public class DrawingField {//extends DrawingFieldContainer {
    private int x;
    private int y;
    private int width;
    private int height;
    private int color = Color.RED;

    private Graphics g = AndroidGraphics.getInstance();

    public DrawingField(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean contains(int i, int j) {
        return i >= x && i <= x + width
                && j >= y && y <= y + height;
    }

    public void draw() {
        g.drawRect(x, y, width, height, color);
    }
}