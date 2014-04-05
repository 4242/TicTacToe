package com.organization4242.tictactoe.view;

import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;

import java.util.ArrayList;

/**
 * Created by ilya on 04.04.14.
 */
public class DrawingFieldContainer extends DrawingField {
    private final Graphics g = AndroidGraphics.getInstance();

    private ArrayList<DrawingField> list;

    public DrawingFieldContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
        list = new ArrayList<DrawingField>(9);
    }

    public void add(DrawingField field) {
        list.add(field);
    }

    public DrawingField get(int index) {
        return list.get(index);
    }

    @Override
    public void draw() {
        super.draw();
        for (DrawingField field : list) {
            field.draw();
        }
    }
}
