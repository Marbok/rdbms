package com.marbok.rdbms.queries;

import com.marbok.rdbms.database.Database;
import com.marbok.rdbms.database.Table;

public class Insert extends Operation {

    public Insert(String query) {
        super(query);
    }

    @Override
    public Table execute(Database database) {
        return null;
    }

    @Override
    protected void parseQuery(String query) {

    }
}
