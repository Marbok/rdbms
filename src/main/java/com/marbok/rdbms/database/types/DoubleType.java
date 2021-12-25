package com.marbok.rdbms.database.types;

import com.marbok.rdbms.database.cells.Cell;
import com.marbok.rdbms.database.cells.DoubleCell;
import com.marbok.rdbms.exception.InvalidTypeException;

public class DoubleType extends Type<Double> {

    public static final DoubleType INSTANCE = new DoubleType();

    private DoubleType() {
        //singleton
    }

    @Override
    protected Double tryCast(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(value + "is not type double", e);
        }
    }

    @Override
    public Cell<Double> createCell(String value) {
        return new DoubleCell(cast(value));
    }
}
