package com.marbok.rdbms.api;

import java.util.Iterator;

import com.marbok.rdbms.database.Row;
import com.marbok.rdbms.database.Table;

public class ResultImpl implements Result {

    private final Table table;
    private final Iterator<Row> rowIterator;

    private Row curr;

    public ResultImpl(Table table) {
        this.table = table;
        rowIterator = table.getRowsIterator();
    }

    @Override
    public Integer getInt(String column) {
        return (Integer) curr.getCell(column).getValue();
    }

    @Override
    public String getString(String column) {
        return (String) curr.getCell(column).getValue();
    }

    @Override
    public Double getDouble(String column) {
        return (Double) curr.getCell(column).getValue();
    }

    @Override
    public void next() {
        curr = rowIterator.next();
    }

    @Override
    public boolean hasNext() {
        return rowIterator.hasNext();
    }
}
