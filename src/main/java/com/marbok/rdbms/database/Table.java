package com.marbok.rdbms.database;

import java.util.Map;

import com.marbok.rdbms.queries.Condition;

public abstract class Table {

    /**
     * postcondition: remove table's data in file system
     */
    abstract void drop();

    /**
     * postcondition: create table in file system
     */
    abstract void create();

    /**
     * precondition: newColumns isn't empty
     * postcondition: change column in table
     */
    abstract void alterTable(Column[] newColumns);

    /**
     * @return rows that match the condition
     */
    abstract Row[] select(Condition condition);

    /**
     * @return rows that match the condition
     */
    abstract Row[] update(Condition condition, Map<Column, Object> newValues);

    /**
     * postcondition: rows that match the condition
     *
     * @return count of removed rows
     */
    abstract int remove(Condition condition);

    /**
     * @return table name
     */
    abstract String name();
}
