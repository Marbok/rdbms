package com.marbok.rdbms.database.cells;

import com.marbok.rdbms.database.types.IntType;
import com.marbok.rdbms.database.types.Type;

public class IntCell extends Cell<Integer> {

    public IntCell(Integer value) {
        super(value, IntType.INSTANCE);
    }
}
