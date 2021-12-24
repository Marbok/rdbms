package com.marbok.rdbms.database.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.marbok.rdbms.database.Column;
import com.marbok.rdbms.database.Row;
import com.marbok.rdbms.database.Table;
import com.marbok.rdbms.queries.Condition;

public class InMemoryTable extends Table {

    protected Collection<Row> rows;

    public InMemoryTable(Collection<Row> rows) {
        this.rows = rows;
    }

    @Override
    public void drop() {

    }

    @Override
    public void create() {

    }

    @Override
    public void alterTable(Column[] newColumns) {

    }

    @Override
    public Row[] select(Condition condition, String[] columns) {
        return rows.stream()
                .filter(condition::test)
                .toArray(Row[]::new);
    }

    @Override
    public Row[] update(Condition condition, Map<Column, Object> newValues) {
        return new Row[0];
    }

    @Override
    public int remove(Condition condition) {
        return 0;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public Iterator<Row> getRowsIterator() {
        return rows.iterator();
    }
}
