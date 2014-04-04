package com.organization4242.tictactoe.view;

import android.graphics.Color;
import android.util.Log;
import com.organization4242.tictactoe.controller.Controller;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;
import com.organization4242.tictactoe.framework.implementations.AndroidInput;
import com.organization4242.tictactoe.model.MainFieldModel;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 30.03.14.
 */
public class GameScreen extends Screen {
    private static final int INSET = 10;

    private DrawingFieldContainer mainField = new DrawingFieldContainer(MainFieldModel.NUMBER_OF_FIELDS);
    private List<DrawingField> baseField = new ArrayList<DrawingField>();
    private List<DrawingField> fields = new ArrayList<DrawingField>();

    private Graphics g = AndroidGraphics.getInstance();

    public GameScreen() {
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            int width = g.getWidth()/3;
            int height = g.getWidth()/3;
            baseField.add(new DrawingField((i / 3) * width, (i % 3) * height, width, height));
            baseField.get(i).setColor(Color.WHITE);
            for (int j = 0; j < MainFieldModel.NUMBER_OF_FIELDS; j++) {
                int internalWidth = width/3;
                int internalHeight = height/3;
                fields.add(new DrawingField(baseField.get(i).getX() + (j / 3) * internalWidth, baseField.get(i).getY() + (j % 3) * internalHeight,
                        internalWidth, internalHeight));

            }
        }
        drawField();
    }

    private void drawField() {
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            baseField.get(i).draw();
            for (int j = 0; j < MainFieldModel.NUMBER_OF_FIELDS; j++) {
                Log.d("----", String.valueOf(fields.get(i+j).getX()));
                Log.d("----", String.valueOf(fields.get(i+j).getY()));
                Log.d("----", "-----");
                fields.get(i + j).draw();
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        if (AndroidInput.getInstance().getTouchEvents().size() > 0) {
            int x = AndroidInput.getInstance().getTouchX(AndroidInput.TouchEvent.TOUCH_DOWN);
            int y = AndroidInput.getInstance().getTouchY(AndroidInput.TouchEvent.TOUCH_DOWN);
            int baseFieldIndex;
            for (int i = 0; i < baseField.size(); i++) {
                DrawingField field = baseField.get(i);
                if (field.contains(x, y)) {
                    baseFieldIndex = i;
                    Log.d("----", String.valueOf(baseFieldIndex));
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        //drawField();
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
