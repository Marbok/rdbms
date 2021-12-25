package com.marbok.rdbms.database.cells;

import com.marbok.rdbms.database.types.StringType;

public class StringCell extends Cell<String> {

    public StringCell(String value) {
        super(value, StringType.INSTANCE);
    }
}
