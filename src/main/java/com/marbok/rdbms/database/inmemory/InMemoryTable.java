package com.marbok.rdbms.database.inmemory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.marbok.rdbms.database.metainformation.Column;
import com.marbok.rdbms.database.Row;
import com.marbok.rdbms.database.Table;
import com.marbok.rdbms.exception.InvalidRowsException;
import com.marbok.rdbms.queries.Condition;

public class InMemoryTable extends Table {

    private final Collection<Row> rows;

    public InMemoryTable(String name, Column[] columns) {
        super(name, columns);
        rows = new ArrayList<>();
    }

    @Override
    public void drop() {
        rows.clear();
    }

    @Override
    public void create() {
        // this table doesn't allocate in file system
    }

    @Override
    public void alterTable(Column[] newColumns) {
        throw new UnsupportedOperationException("Alter table is unsupported yet");
    }

    @Override
    public Row[] select(Condition condition, String[] columns) {
        return rows.stream()
                .filter(condition::test)
                .toArray(Row[]::new);
    }

    @Override
    public Row[] selectAll(String[] columns) {
        return rows.toArray(Row[]::new);
    }

    @Override
    public Row[] update(Condition condition, Map<Column, Object> newValues) {
        throw new UnsupportedOperationException("Update is unsupported yet");
    }

    @Override
    public void insertRow(Row row) {
        if (!equalColumns(row.getColumns())) {
            throw new InvalidRowsException();
        }
        rows.add(row);
    }

    @Override
    public int remove(Condition condition) {
        throw new UnsupportedOperationException("Delete is unsupported yet");
    }

    @Override
    public Iterator<Row> getRowsIterator() {
        return rows.iterator();
    }

    public void insertRows(Collection<Row> rows) {
        final long countNotValidRows = rows.stream()
                .filter(row -> !equalColumns(row.getColumns()))
                .count();
        if (countNotValidRows > 0) {
            throw new InvalidRowsException();
        }
        this.rows.addAll(rows);
    }

    private boolean equalColumns(Column[] rowColumns) {
        if (rowColumns.length != columns.length) {
            return false;
        }
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i].getName().equals(rowColumns[i].getName()) ||
                    columns[i].getType() != rowColumns[i].getType()) {
                return false;
            }
        }
        return true;
    }
}
