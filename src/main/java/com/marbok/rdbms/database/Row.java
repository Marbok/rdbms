package com.marbok.rdbms.database;

public abstract class Row {

    /**
     * precondition: row has column and value has type clazz
     *
     * @return value form certain column
     */
    public abstract Object getValue(Column column);

    public abstract Column getColumn(String columnName);

    /**
     * @return merge this row and consume row in one
     */
    public abstract Row merge(Row row);

    /**
     * precondition: column exists
     * @param column
     * @return
     */
    public abstract Cell<?> getCell(String column);
}
