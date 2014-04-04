package com.organization4242.tictactoe.view;

import android.graphics.Color;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;
import com.organization4242.tictactoe.model.MainField;

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

    public DrawingField(int width, int height) {
        //super(width, height);
        this.width = (int) (width /
                Math.sqrt(MainField.NUMBER_OF_FIELDS));
        this.height = (int) (height /
                Math.sqrt(MainField.NUMBER_OF_FIELDS));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x * width;
    }

    public void setY(int y) {
        this.y = y * height;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isInside(int i, int j) {
        return false;
    }

    public void draw() {
        g.drawRect(x, y, width, height, color);
    }
}