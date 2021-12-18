package com.marbok.rdbms.exception;

public class TableNotExistException extends RuntimeException {
    public TableNotExistException(String message) {
        super(message);
    }
}
