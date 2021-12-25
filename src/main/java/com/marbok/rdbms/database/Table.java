package com.marbok.rdbms.database;

import java.util.Iterator;
import java.util.Map;

import com.marbok.rdbms.database.metainformation.Column;
import com.marbok.rdbms.queries.Condition;

public abstract class Table {

    protected final String name;
    protected final Column[] columns;

    protected Table(String name, Column[] columns) {
        this.name = name;
        this.columns = columns;
    }

    /**
     * postcondition: remove table's data in file system.
     */
    public abstract void drop();

    /**
     * postcondition: create table in file system.
     */
    public abstract void create();

    /**
     * precondition: newColumns isn't empty.
     * postcondition: change column in table
     */
    public abstract void alterTable(Column[] newColumns);

    /**
     * @return rows that match the condition
     */
    public abstract Row[] select(Condition condition, String[] columns);

    public abstract Row[] selectAll(String[] columns);

    /**
     * @return rows that match the condition
     */
    public abstract Row[] update(Condition condition, Map<Column, Object> newValues);

    /**
     * postcondition: insert new row in table.
     */
    public abstract void insertRow(Row values);

    /**
     * postcondition: rows that match the condition.
     *
     * @return count of removed rows
     */
    public abstract int remove(Condition condition);

    /**
     * @return iterator for rows in table
     */
    public abstract Iterator<Row> getRowsIterator();

    /**
     * @return columns in table
     */
    public Column[] getColumns() {
        return columns;
    }

    /**
     * @return table name
     */
    public String name() {
        return name;
    }
}
