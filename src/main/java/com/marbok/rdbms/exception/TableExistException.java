package com.marbok.rdbms.exception;

public class TableExistException extends RuntimeException {
    public TableExistException(String message) {
        super(message);
    }
}
