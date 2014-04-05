package com.organization4242.tictactoe.view;

import android.graphics.Color;
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

    private List<DrawingFieldContainer> field = new ArrayList<DrawingFieldContainer>();

    private byte[] coordinates;

    private Graphics g = AndroidGraphics.getInstance();

    public GameScreen() {
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            int numberOfColumns = (int) Math.sqrt(MainFieldModel.NUMBER_OF_FIELDS);
            int width = g.getWidth()/numberOfColumns;

            //Adding 9 big fields, corresponding to baseField in MainFieldModel
            field.add(new DrawingFieldContainer((i % numberOfColumns) * width, (i / numberOfColumns) * width, width, width));
            field.get(i).setColor(Color.BLACK);

            //Adding 9 internal fields to each field in base fields
            for (int j = 0; j < MainFieldModel.NUMBER_OF_FIELDS; j++) {
                field.get(i).add(new DrawingField(field.get(i).getX() + (j % numberOfColumns) * width / numberOfColumns,
                        field.get(i).getY() + (j / numberOfColumns) * width /numberOfColumns,
                        width/numberOfColumns, width /numberOfColumns));
                field.get(i).get(j).setColor(Color.WHITE);
            }
        }
        drawField();
    }

    private void drawField() {
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            field.get(i).draw();
        }
    }

    private void clear() {
        g.clear(Color.BLACK);
    }

    @Override
    public void update(float deltaTime) {
        if (AndroidInput.getInstance().getTouchEvents().size() > 0) {
            int x = AndroidInput.getInstance().getTouchX(AndroidInput.TouchEvent.TOUCH_DOWN);
            int y = AndroidInput.getInstance().getTouchY(AndroidInput.TouchEvent.TOUCH_DOWN);
            for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
                if (field.get(i).contains(x, y)) {
                    for (int j = 0; j < MainFieldModel.NUMBER_OF_FIELDS; j++) {
                        if (field.get(i).get(j).contains(x, y)) {
                            firePropertyChange(Controller.VIEW_UPDATED, coordinates,
                                    new byte[]{(byte) i, (byte) j});
                            coordinates = new byte[]{(byte) i, (byte) j};
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        clear();
        firePropertyChange(Controller.DISPOSING, 0, 1);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent pce) {
        MainFieldModelMessage message = (MainFieldModelMessage) pce.getNewValue();
        field.get(message.getI()).get(message.getJ()).setColor(FieldColor.fromState(message.getOrder()));
        field.get(message.getI()).setColor(Color.BLACK);
        field.get(message.getI()).draw();
        if (pce.getPropertyName().equals(Controller.LOCAL_WIN)
                || pce.getPropertyName().equals(Controller.WIN)) {
            field.get(message.getI()).setColor(FieldColor.fromState(message.getOrder()));
            field.get(message.getI()).draw();
        }
        if (message.getActiveField() != MainFieldModel.ANY) {
            field.get(message.getActiveField()).setColor(Color.GREEN);
            field.get(message.getActiveField()).draw();
        }
    }
}
