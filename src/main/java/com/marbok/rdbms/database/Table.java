package com.marbok.rdbms.database;

import java.util.Iterator;
import java.util.Map;

import com.marbok.rdbms.queries.Condition;

public abstract class Table {

    /**
     * postcondition: remove table's data in file system
     */
    public abstract void drop();

    /**
     * postcondition: create table in file system
     */
    public abstract void create();

    /**
     * precondition: newColumns isn't empty
     * postcondition: change column in table
     */
    public abstract void alterTable(Column[] newColumns);

    /**
     * @return rows that match the condition
     */
    public abstract Row[] select(Condition condition, String[] columns);

    /**
     * @return rows that match the condition
     */
    public abstract Row[] update(Condition condition, Map<Column, Object> newValues);

    /**
     * postcondition: rows that match the condition
     *
     * @return count of removed rows
     */
    public abstract int remove(Condition condition);

    /**
     * @return table name
     */
    public abstract String name();

    /**
     * @return iterator for rows in table
     */
    public abstract Iterator<Row> getRowsIterator();
}
