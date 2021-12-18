package com.marbok.rdbms.database;

public abstract class Row {

    /**
     * precondition: row has column and value has type clazz
     *
     * @return value form certain column
     */
    abstract <T> T getValue(Column column, Class<T> clazz);

    /**
     * @return merge this row and consume row in one
     */
    abstract Row merge(Row row);
}
