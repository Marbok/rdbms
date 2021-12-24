package com.marbok.rdbms.queries;

import com.marbok.rdbms.database.Row;

public interface Condition {
    boolean test(Row row);
}
