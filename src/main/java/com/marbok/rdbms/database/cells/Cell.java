package com.marbok.rdbms.database.cells;

import com.marbok.rdbms.database.types.Type;

public abstract class Cell<T extends Comparable<T>> {

    private final T value;
    private final Type<T> type;

    public Cell(T value, Type<T> type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public Type<T> getType() {
        return type;
    }

    private Cell<T> copyCellWithNewValue(String value) {
        return type.createCell(value);
    }


    public boolean eq(String value) {
        Cell<T> cell = copyCellWithNewValue(value);

        if (getValue() == null && cell.getValue() == null) {
            return true;
        }
        if (getValue() == null) {
            return false;
        }
        return getValue().equals(cell.getValue());
    }

    public boolean ne(String value) {
        return !eq(value);
    }

    public boolean lt(String value) {
        Cell<T> cell = copyCellWithNewValue(value);

        if (getValue() == null && cell.getValue() == null) {
            return false;
        }
        if (getValue() == null) {
            return true;
        }
        if (cell.getValue() == null) {
            return false;
        }
        return getValue().compareTo(cell.getValue()) < 0;
    }

    public boolean gt(String value) {
        Cell<T> cell = copyCellWithNewValue(value);

        if (getValue() == null && cell.getValue() == null) {
            return false;
        }
        if (getValue() == null) {
            return false;
        }
        if (cell.getValue() == null) {
            return true;
        }
        return getValue().compareTo(cell.getValue()) > 0;
    }
}
