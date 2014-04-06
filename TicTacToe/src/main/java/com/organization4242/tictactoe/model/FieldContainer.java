package com.organization4242.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 06.04.14.
 */
public class FieldContainer extends Field {
    private List<FieldInterface> list;

    public FieldContainer(int capacity) {
        super(capacity);
        list = new ArrayList<FieldInterface>(capacity);
    }

    public void add(FieldInterface field) {
        list.add(field);
    }

    public FieldInterface getField(int index) {
        return list.get(index);
    }

    public List<Byte> getAvailableFields() {
        List<Byte> emptyFields = new ArrayList<Byte>();
        for (int i = 0; i < list.size(); i++) {
            FieldInterface f = list.get(i);
            if (f.getEmptyFields().size() > 0) {
                emptyFields.add((byte) i);
            }
        }
        return emptyFields;
    }
}
