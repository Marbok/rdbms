package com.marbok.rdbms.database;

import java.util.HashMap;
import java.util.Map;

import com.marbok.rdbms.database.metainformation.Column;
import com.marbok.rdbms.exception.TableExistException;
import com.marbok.rdbms.exception.TableNotExistException;

public abstract class Database {

    protected Map<String, Table> tables = new HashMap<>();

    /**
     * precondition: table doesn't exist
     * postcondition: create table in database
     */
    public void createTable(Table table) {
        if (tables.containsKey(table.name())) {
            throw new TableExistException(table.name() + " exists");
        }
        tables.put(table.name(), table);
        table.create();
    }

    /**
     * precondition: table with the passed name does not exist, all columns is unique
     * postcondition: create table in database
     */
    public final void createTable(String tableName, Column[] columns) {
        if (tables.containsKey(tableName)) {
            throw new TableExistException(tableName + " exists");
        }
        final Table newTable = buildTable(tableName, columns);
        tables.put(tableName, newTable);
    }

    /**
     * precondition: columns isn't empty, all columns is unique
     *
     * @return new table
     */
    protected abstract Table buildTable(String tableName, Column[] columns);

    /**
     * precondition: table is exist
     * postcondition: remove table from database
     */
    public void dropTable(String tableName) {
        if (!tables.containsKey(tableName)) {
            throw new TableNotExistException(tableName + " doesn't exist");
        }
        tables.get(tableName).drop();
    }

    /**
     * precondition: table exists
     *
     * @return table with consumed name
     */
    public Table getTable(String tableName) {
        final Table table = tables.get(tableName);
        if (table == null) {
            throw new TableNotExistException("Table with name '" + tableName + "' doesn't exist");
        }
        return table;
    }
}
