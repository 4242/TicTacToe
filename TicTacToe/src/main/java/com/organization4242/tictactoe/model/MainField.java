package com.organization4242.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 31.03.14.
 */
public final class MainField {
    public static final int NUMBER_OF_FIELDS = 3;
    public static final Byte EMPTY = 0;
    public static final Byte O = -1;
    public static final Byte X = 1;

    private boolean order;
    private byte activeField;
    private List<Byte> baseField;
    private List<List<List<Byte>>> fields;

    public MainField setOrder(boolean order) {
        this.order = order;
        return this;
    }

    public MainField setActiveField(byte activeField) {
        this.activeField = activeField;
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

    public boolean getOrder() {
        return order;
    }

    public byte getActiveField() {
        return activeField;
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

    public static MainField getInstance() {
        return instance;
    }


    public static boolean isWinBoolean (List<List<Integer>> matrix, boolean order) {
        int sums[] = {0,0,0,0,0,0,0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sums[i]+=matrix.get(i).get(j);
                sums[i+3]+=matrix.get(j).get(i);
            }
            sums[6]+=matrix.get(i).get(i);
            sums[7]+=matrix.get(i).get(3-i);
        }
        for (int i = 0; i < 8; i++) {
            if( order && sums[i]== 3) {
                return true;
            }
            if(!order && sums[i]==-3) {
                return true;
            }
        }
        return false;
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
                return 0;
            }
        }
        return 0;
    }
}
