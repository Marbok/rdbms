package com.marbok.rdbms.queries;

import com.marbok.rdbms.database.Row;

public class TrueCondition implements Condition {
    @Override
    public boolean test(Row row) {
        return true;
    }
}
