package com.organization4242.tictactoe.view;

import android.graphics.Color;
import com.organization4242.tictactoe.controller.Controller;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;
import com.organization4242.tictactoe.model.MainField;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 30.03.14.
 */
public class GameScreen extends Screen {
    private static final int INSET = 10;

    private DrawingFieldContainer mainField = new DrawingFieldContainer(MainField.NUMBER_OF_FIELDS);
    private List<DrawingField> baseField = new ArrayList<DrawingField>();
    private List<DrawingField> fields = new ArrayList<DrawingField>();

    private Graphics g = AndroidGraphics.getInstance();

    public GameScreen() {
        for (int i = 0; i < MainField.NUMBER_OF_FIELDS; i++) {
            baseField.add(new DrawingField(g.getWidth(), g.getWidth()));
            baseField.get(i).setX(i % 3);
            baseField.get(i).setY((i % 3));
            baseField.get(i).setColor(Color.BLUE);
            for (int j = 0; j < MainField.NUMBER_OF_FIELDS; j++) {
                fields.add(new DrawingField(baseField.get(i).getWidth(), baseField.get(i).getHeight()));
                fields.get(j).setX(j % 3);
                fields.get(j).setX(j % 3);
            }
        }
    }

    private void drawField() {
        for (int i = 0; i < MainField.NUMBER_OF_FIELDS; i++) {
            baseField.get(i).draw();
            for (int j = 0; j < MainField.NUMBER_OF_FIELDS; j++) {
                fields.get(i + j).draw();
            }
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        drawField();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        firePropertyChange(Controller.DISPOSING, 0, 1);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent pce) {
        drawField();
    }
}
