package com.marbok.rdbms.database.types;

import com.marbok.rdbms.database.cells.Cell;

public abstract class Type<T extends Comparable<T>> {

    public static final String NULL = "null";

    /**
     * precondition: value is T.
     *
     * @return value with T type
     */
    protected abstract T tryCast(String value);

    /**
     * @return create cell with right type
     */
    public abstract Cell<T> createCell(String value);

    /**
     * precondition: value is T type or "null".
     *
     * @return value with T type or null
     */
    public final T cast(String value) {
        if (value == null || value.equals(NULL)) {
            return null;
        }
        return tryCast(value);
    }
}
