package org.example;

import java.util.ArrayList;

public class Table {
    private ArrayList<Record> table = new ArrayList<>();

    public int getMax() {
        int max = 0;
        for (Record record : table)
            if (record.getValue() > max)
                max = record.getValue();
        return max;
    }

    public void addRecord(StringBuffer field) {
        for (Record record : table) {
            if(Lab3.equals(record.getField(), field)) {
                record.incValue();
            }}
        table.add(new Record(field, 1));
    }
}
