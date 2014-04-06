package com.organization4242.tictactoe.view;

import android.graphics.Color;
import android.util.Log;
import com.organization4242.tictactoe.controller.Controller;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;
import com.organization4242.tictactoe.model.MainFieldModel;
import com.organization4242.tictactoe.model.MainFieldModelMessage;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ilya on 30.03.14.
 */
public class GameScreen extends Screen {
    private static final int INSET = 10;
    private static final String TAG = "Game screen: ";

    private List<DrawingFieldContainer> field = new ArrayList<DrawingFieldContainer>();

    private byte[] coordinates;

    private Graphics g = AndroidGraphics.getInstance();

    public GameScreen() {
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            int numberOfColumns = (int) Math.sqrt(MainFieldModel.NUMBER_OF_FIELDS);
            int width = g.getWidth() < g.getHeight()
                    ? g.getWidth()/numberOfColumns
                    : g.getHeight()/numberOfColumns;

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
        if (pce.getPropertyName().equals(Controller.LOCAL_WIN)) {
            field.get(message.getI()).setColor(FieldColor.fromState(message.getOrder()));
            field.get(message.getI()).draw();
        }
        if (message.getActiveField() != MainFieldModel.ANY) {
            field.get(message.getActiveField()).setColor(Color.GREEN);
            field.get(message.getActiveField()).draw();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        int[] c = (int[]) o;
        int x = c[0];
        int y = c[1];
        for (int i = 0; i < MainFieldModel.NUMBER_OF_FIELDS; i++) {
            if (field.get(i).contains(x, y)) {
                for (int j = 0; j < MainFieldModel.NUMBER_OF_FIELDS; j++) {
                    if (field.get(i).get(j).contains(x, y)) {
                        Log.d(TAG, String.format("View changed event, coordinates: %d, %d", i, j));
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
