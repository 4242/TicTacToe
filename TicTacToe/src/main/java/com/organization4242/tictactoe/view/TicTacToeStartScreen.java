package com.organization4242.tictactoe.view;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.organization4242.tictactoe.app.TicTacToeController;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;
import com.organization4242.tictactoe.framework.implementations.AndroidInput;
import com.organization4242.tictactoe.model.MainField;

import java.beans.PropertyChangeEvent;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeStartScreen extends Screen{
    StringBuilder builder = new StringBuilder();

    public TicTacToeStartScreen() {
        drawField();
    }
    private void drawField(){
        Graphics g = AndroidGraphics.getInstance();
        int cl = 0;
        for (int i = 0; i < MainField.getInstance().getBaseField().size(); i++) {
            switch (MainField.getInstance().getBaseField().get(i)) {
                case -1 :
                    cl = Color.BLUE;
                    break;
                case 0 :
                    cl = Color.WHITE;
                    break;
                case 1 :
                    cl = Color.RED;
                    break;
            }
            g.drawRect((i/3)*200, (i%3)*200, 199, 199, cl);
        }
        for (int i = 0; i < 9; i++) {
            int fieldX = i % MainField.NUMBER_OF_FIELDS;
            int fieldY = i / MainField.NUMBER_OF_FIELDS;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    switch (MainField.getInstance().getFields().get(i).get(j).get(k)) {
                        case -1:
                            cl = Color.BLUE;
                            break;
                        case 0:
                            cl = Color.GRAY;
                            break;
                        case 1:
                            cl = Color.RED;
                            break;
                    }
                    g.drawRect(10*(1 + fieldX) + fieldX * (56*MainField.NUMBER_OF_FIELDS+22) + k * 62,
                            10*(1 + fieldY) + fieldY * (56*MainField.NUMBER_OF_FIELDS+22) + j * 62,
                            56, 56, cl);
                }
            }
        }
    }
    @Override
    public void update(float deltaTime) {
        int touchedMainFieldX, touchedMainFieldY, touchedFieldX, touchedFieldY;
        AndroidInput input = AndroidInput.getInstance();
        for (int i = 0; i <20; i++){
            if (AndroidInput.getInstance().isTouchDown(i)){
                touchedMainFieldX = AndroidInput.getInstance().getTouchX(i)/200;
                touchedMainFieldY = AndroidInput.getInstance().getTouchY(i)/200;
                touchedFieldX = AndroidInput.getInstance().getTouchX(i)/60 %3;     // пересчитать координаты потом
                touchedFieldY = AndroidInput.getInstance().getTouchY(i)/60 %3;     // здесь тоже
                if((MainField.getInstance().getActiveField() == 0) ||
                        (MainField.getInstance().getActiveField() == touchedMainFieldX+touchedMainFieldY*MainField.NUMBER_OF_FIELDS) )
                    ;
                firePropertyChange(TicTacToeController.VIEW_UPDATED, 0,
                        new byte[]{(byte) (touchedMainFieldX+3*touchedMainFieldY), (byte) touchedFieldX, (byte) touchedFieldY});
                builder.setLength(0);
                builder.append(touchedMainFieldX+3*touchedMainFieldY);
                builder.append(' ');
                builder.append(touchedFieldX);
                builder.append(' ');
                builder.append(touchedFieldY);
                Log.d("Touch coords",builder.toString());
            }
        }
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

    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent pce) {
    }
}
