package com.marbok.rdbms.api;

public interface Result {

    /**
     * precondition: row has column
     *
     * @return value from column
     */
    int getInt(String column);

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
    double getDouble(String column);

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
