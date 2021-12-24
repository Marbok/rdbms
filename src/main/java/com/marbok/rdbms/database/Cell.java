package com.marbok.rdbms.database;

public abstract class Cell<T> {

    public abstract T getValue();
    public abstract boolean eq(String value);
    public abstract boolean ne(String value);
    public abstract boolean lt(String value);
    public abstract boolean gt(String value);
}
