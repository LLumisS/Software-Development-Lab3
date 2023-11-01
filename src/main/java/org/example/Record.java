package org.example;

public class Record {
    private StringBuffer field;
    private int value;

    Record(StringBuffer field, int value) {
        this.field = field;
        this.value = value;
    }

    public void incValue() {
        value++;
    }

    public StringBuffer getField() {
        return field;
    }

    public int getValue() {
        return value;
    }
}
