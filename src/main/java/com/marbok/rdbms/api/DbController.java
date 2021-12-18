package com.marbok.rdbms.api;

public abstract class DbController {

    /**
     * precondition: query is valid
     * @return
     */
    public abstract Result execute(String query);
}
