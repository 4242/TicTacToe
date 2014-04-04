package com.organization4242.tictactoe.view;

import android.graphics.Color;
import android.text.method.Touch;
import android.util.Log;
import com.organization4242.tictactoe.app.R;
import com.organization4242.tictactoe.app.TicTacToeController;
import com.organization4242.tictactoe.framework.Graphics;
import com.organization4242.tictactoe.framework.Input;
import com.organization4242.tictactoe.framework.Screen;
import com.organization4242.tictactoe.framework.implementations.AndroidGraphics;
import com.organization4242.tictactoe.framework.implementations.AndroidInput;
import com.organization4242.tictactoe.model.MainField;

import java.beans.PropertyChangeEvent;
import java.util.List;

/**
 * Created by ilya on 30.03.14.
 */
public class TicTacToeStartScreen extends Screen{
    enum GameState{
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;

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
                    if (i == MainField.getInstance().getActiveField() ||
                            MainField.getInstance().getActiveField() == MainField.ANY) {
                        cl = Color.BLUE;
                    } else {
                        cl = Color.BLUE;
                    }
                    break;
                case 0 :
                    if (i == MainField.getInstance().getActiveField() ||
                            MainField.getInstance().getActiveField() == MainField.ANY) {
                        cl = Color.GRAY;
                    } else {
                        cl = Color.DKGRAY;
                    }
                    break;
                case 1 :
                    if (i == MainField.getInstance().getActiveField() ||
                            MainField.getInstance().getActiveField() == MainField.ANY) {
                        cl = Color.RED;
                    } else {
                        cl = Color.RED;
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
                            cl = Color.rgb(255, 255, 255);
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
        List<Input.TouchEvent> touchEvents = AndroidInput.getInstance().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents);
    }

    private void updateReady(List<Input.TouchEvent> touchEvents)
    {
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<Input.TouchEvent> touchEvents)
    {
        Input.TouchEvent event;
        int len = touchEvents.size();
        int touchedMainFieldX, touchedMainFieldY, touchedFieldX, touchedFieldY;
        for (int i = 0; i < len; i++){
            event = touchEvents.get(i);
            if (event.getType() == Input.TouchEvent.TOUCH_UP){
                touchedMainFieldX = event.getX()/190;
                touchedMainFieldY = event.getY()/190;
                touchedFieldX = (event.getX() % 190 - 5) / 60;
                touchedFieldY = (event.getY() % 190 - 5) / 60;
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
    //    if (state == GameState.Running)
    //        state = GameState.Paused;
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
