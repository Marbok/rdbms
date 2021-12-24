package com.marbok.rdbms.queries;

import com.marbok.rdbms.database.Database;
import com.marbok.rdbms.database.Table;

public abstract class Operation {

    protected final String query;

    protected Operation(String query) {
        this.query = query;
        parseQuery(query);
    }

    public abstract Table execute(Database database);

    protected abstract void parseQuery(String query);
}
