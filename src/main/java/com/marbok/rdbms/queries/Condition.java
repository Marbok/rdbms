package com.marbok.rdbms.queries;

import com.marbok.rdbms.database.Row;

public abstract class Condition {
    abstract boolean test(Row row);
}
