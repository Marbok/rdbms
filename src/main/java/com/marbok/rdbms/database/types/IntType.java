package com.marbok.rdbms.database.types;

import com.marbok.rdbms.database.cells.Cell;
import com.marbok.rdbms.database.cells.IntCell;
import com.marbok.rdbms.exception.InvalidTypeException;

public class IntType extends Type<Integer> {

    public static final IntType INSTANCE = new IntType();

    private IntType() {
        // singleton
    }

    @Override
    public Integer tryCast(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new InvalidTypeException(value + "is not type int", e);
        }
    }

    @Override
    public Cell<Integer> createCell(String value) {
        return new IntCell(cast(value));
    }
}
