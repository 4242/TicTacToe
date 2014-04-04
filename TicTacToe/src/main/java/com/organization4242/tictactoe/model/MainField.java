package com.organization4242.tictactoe.model;

import com.organization4242.tictactoe.ai.AI;
import com.organization4242.tictactoe.ai.TicTacToeAI;
import com.organization4242.tictactoe.app.TicTacToeController;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public final class MainField extends AbstractModel {
    public static final int NUMBER_OF_FIELDS = 3;
    public static final int NUMBER_OF_FIELD = 3;
    public static final Byte EMPTY = 0;
    public static final Byte O = -1;
    public static final Byte X = 1;
    public static final Byte ANY = 10;

    private Byte order;
    private byte activeField = ANY;
    private byte previousField = 10;
    private List<Byte> baseField;
    private List<List<List<Byte>>> fields;

    public MainField setOrder(Byte order) {
        this.order = order;
        return this;
    }

    public MainField setActiveField(byte activeField) {
        this.activeField = activeField;
        return this;
    }

    public MainField setPreviousField(byte previousField) {
        this.previousField = previousField;
        return this;
    }

    public MainField setBaseField(List<Byte> baseField) {
        this.baseField = baseField;
        return this;
    }

    public MainField setFields(List<List<List<Byte>>> fields) {
        this.fields = fields;
        return this;
    }

    public Byte getOrder() {
        return order;
    }

    public byte getActiveField() {
        return activeField;
    }

    public byte getPreviousField() {
        return previousField;
    }

    public List<Byte> getBaseField() {
        return baseField;
    }

    public List<List<List<Byte>>> getFields() {
        return fields;
    }

    private static MainField instance = new MainField();

    private MainField() {
        //Initialize base field with zero elements
        baseField = new ArrayList<Byte>();
        for (int i = 0; i < Math.pow(NUMBER_OF_FIELDS, 2); i++) {
            baseField.add(EMPTY);
        }

        //Initialize fields with zero elements
        fields = new ArrayList<List<List<Byte>>>();
        for (int i = 0; i < baseField.size(); i++) {
            fields.add(new ArrayList<List<Byte>>());
            for (int j = 0; j < baseField.size(); j++) {
                fields.get(i).add(new ArrayList<Byte>());
                for (Byte b : baseField) {
                    fields.get(i).get(j).add(EMPTY);
                }
            }
        }
    }

    public List<byte[]> freePoints() {
        List<byte[]> points = new ArrayList<byte[]>();
        List<List<Byte>> currentField = new ArrayList<List<Byte>>();
        currentField.addAll(fields.get(activeField));
        for (int i = 0; i < MainField.NUMBER_OF_FIELD; i++) {
            for (int j = 0; j < MainField.NUMBER_OF_FIELD; j++) {
                if (currentField.get(i).get(j).equals(MainField.EMPTY)) {
                    points.add(new byte[]{(byte) i,(byte) j});
                }
            }
        }
        return points;
    }

    private boolean isFilled(List<List<Byte>> field) {
        boolean res = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                res = res && (field.get(i).get(j) != EMPTY);
        return res;
    }

    @Override
    public void viewPropertyChange(PropertyChangeEvent pce) {
        byte[] coordinates = (byte[]) pce.getNewValue();

        if ((coordinates[0] == activeField || activeField == ANY)
                && fields.get(coordinates[0]).get(coordinates[2]).get(coordinates[1]).equals(EMPTY)) {
            fields.get(coordinates[0]).get(coordinates[2]).set(coordinates[1], order);
            order = (byte) ((byte) -1 * order);
            previousField = activeField;
            activeField = (byte) (coordinates[1] + coordinates[2] * 3);
            if (winner(fields.get(coordinates[0])).equals(X) && (baseField.get(coordinates[0]) == EMPTY)) {
                baseField.set(coordinates[0], X);
            } else if (winner(fields.get(coordinates[0])).equals(O) && (baseField.get(coordinates[0]) == EMPTY)) {
                baseField.set(coordinates[0], O);
            }
            if (isFilled(fields.get(activeField))) activeField = ANY;
            
            AI ai = new TicTacToeAI(this);
            byte[] move = ai.nextMove();
            
            fields.get(activeField).get(move[0]).set(move[1], order);
            order = (byte) ((byte) -1 * order);
            if (winner(fields.get(activeField)).equals(X) && (baseField.get(activeField) == EMPTY)) {
                baseField.set(move[0], X);
            } else if (winner(fields.get(activeField)).equals(O) && (baseField.get(activeField) == EMPTY)) {
                baseField.set(move[0], O);   
            }
            
            activeField = (byte)(move[0] + 3 * move[1]);

            firePropertyChange(TicTacToeController.MODEL_UPDATED, 0, 1);
        }
    }

    public static MainField getInstance() {
        return instance;
    }

    public static Byte winner(List<List<Byte>> matrix) {
        int sums[] = {0,0,0,0,0,0,0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sums[i]+=matrix.get(i).get(j);
                sums[i+3]+=matrix.get(j).get(i);
            }
            sums[6]+=matrix.get(i).get(i);
            sums[7]+=matrix.get(i).get(2-i);
        }
        for (int i = 0; i < 8; i++) {
            if(sums[i] ==-3) {
                return -1;
            } else if (sums[i]== 3) {
                return 1;
            } else {
            }
        }
        return 0;
    }
}
