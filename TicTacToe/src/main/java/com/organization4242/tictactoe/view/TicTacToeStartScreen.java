package com.organization4242.tictactoe.view;

import android.graphics.Color;
import android.util.Log;
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
        int fieldX, fieldY;
        Graphics g = AndroidGraphics.getInstance();
        int cl = 0;
        for (int i = 0; i < MainField.getInstance().getBaseField().size(); i++) {
            switch (MainField.getInstance().getBaseField().get(i)) {
                case -1 :
                    if (i == MainField.getInstance().getActiveField() || MainField.getInstance().getActiveField() == 10) {
                        cl = Color.rgb(0, 0, 255);
                    } else {
                        cl = Color.rgb(0, 0, 128);
                    }
                    break;
                case 0 :
                    if (i == MainField.getInstance().getActiveField() || MainField.getInstance().getActiveField() == 10) {
                        cl = Color.rgb(150, 150, 150);
                    } else {
                        cl = Color.rgb(75, 75, 75);
                    }
                    break;
                case 1 :
                    if (i == MainField.getInstance().getActiveField() || MainField.getInstance().getActiveField() == 10) {
                        cl = Color.rgb(255, 0, 0);
                    } else {
                        cl = Color.rgb(128, 0, 128);
                    }
                    break;
            }
            fieldX = i % 3;
            fieldY = i / 3;
            g.drawRect(1 + fieldX * 190, 1 + fieldY * 190, 188, 188, cl);
        }
        for (int i = 0; i < 9; i++) {
            fieldX = i % 3;
            fieldY = i / 3;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    switch (MainField.getInstance().getFields().get(i).get(j).get(k)) {
                        case -1:
                            cl = Color.BLUE;
                            break;
                        case 0:
                            cl = Color.WHITE;
                            break;
                        case 1:
                            cl = Color.RED;
                            break;
                    }
                    g.drawRect(10 + fieldX * 190 + k * 60, 10 + fieldY * 190 + j * 60,
                            50, 50, cl);
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
                touchedMainFieldX = AndroidInput.getInstance().getTouchX(i)/190;
                touchedMainFieldY = AndroidInput.getInstance().getTouchY(i)/190;
                touchedFieldX = (AndroidInput.getInstance().getTouchX(i) % 190 - 5) / 60;
                touchedFieldY = (AndroidInput.getInstance().getTouchY(i) % 190 - 5) / 60;
                if ( (touchedMainFieldX < 3) && (touchedFieldY < 3) ) {
                    firePropertyChange(TicTacToeController.VIEW_UPDATED, 0,
                            new byte[]{(byte) (touchedMainFieldX + 3 * touchedMainFieldY), (byte) touchedFieldX, (byte) touchedFieldY});
                }
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
        drawField();
    }
}
