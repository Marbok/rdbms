package com.marbok.rdbms.api;

import com.marbok.rdbms.database.types.DoubleType;

public interface Result {

    /**
     * precondition: row has column
     *
     * @return value from column
     */
    Integer getInt(String column);

    /**
     * precondition: row has column
     *
     * @return value from column
     */
    String getString(String column);

    /**
     * precondition: row has column
     *
     * @return value from column
     */
    Double getDouble(String column);

    /**
     * precondition: result has next row
     * postcondition: change result on next row
     */
    void next();

    /**
     * @return true - if result has next row
     */
    boolean hasNext();
}
