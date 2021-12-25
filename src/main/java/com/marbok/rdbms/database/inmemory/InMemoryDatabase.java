package com.marbok.rdbms.database.inmemory;

import com.marbok.rdbms.database.Database;
import com.marbok.rdbms.database.Table;
import com.marbok.rdbms.database.metainformation.Column;

public class InMemoryDatabase extends Database {

    @Override
    protected Table buildTable(String tableName, Column[] columns) {
        return new InMemoryTable(tableName, columns);
    }
}
