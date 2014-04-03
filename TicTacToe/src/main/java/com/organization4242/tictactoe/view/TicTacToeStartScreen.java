package com.organization4242.tictactoe.view;

import android.graphics.Color;
import android.util.Log;
import com.organization4242.tictactoe.app.R;
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
                        cl = R.color.LightBlue;
                    } else {
                        cl = R.color.Blue;
                    }
                    break;
                case 0 :
                    if (i == MainField.getInstance().getActiveField() || MainField.getInstance().getActiveField() == 10) {
                        cl = R.color.LightGray;
                    } else {
                        cl = R.color.Gray;
                    }
                    break;
                case 1 :
                    if (i == MainField.getInstance().getActiveField() || MainField.getInstance().getActiveField() == 10) {
                        cl = R.color.LightRed;
                    } else {
                        cl = R.color.Red;
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
                            cl = R.color.DarkBlue;
                            break;
                        case 0:
                            cl = R.color.White;
                            break;
                        case 1:
                            cl = R.color.DarkRed;
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
