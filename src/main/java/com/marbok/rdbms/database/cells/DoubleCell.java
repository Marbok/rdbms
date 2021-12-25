package com.marbok.rdbms.database.cells;

import com.marbok.rdbms.database.types.DoubleType;

public class DoubleCell extends Cell<Double> {

    public DoubleCell(Double value) {
        super(value, DoubleType.INSTANCE);
    }
}
