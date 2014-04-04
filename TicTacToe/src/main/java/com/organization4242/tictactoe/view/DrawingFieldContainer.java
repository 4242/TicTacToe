package com.organization4242.tictactoe.view;

import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;

import java.util.ArrayList;

/**
 * Created by ilya on 04.04.14.
 */
public class DrawingFieldContainer {
    private final Graphics g = AndroidGraphics.getInstance();

    private ArrayList<DrawingField> list;

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DrawingFieldContainer(int capacity) {
        list = new ArrayList<DrawingField>(capacity);
    }

    public DrawingFieldContainer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void add(DrawingField field) {
        list.add(field);
    }

    public DrawingField get(int index) {
        return list.get(index);
    }

    public void draw() {

    }
}
