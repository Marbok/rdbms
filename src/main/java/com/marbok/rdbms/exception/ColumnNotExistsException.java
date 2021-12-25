package com.marbok.rdbms.exception;

public class ColumnNotExistsException extends RuntimeException {
    public ColumnNotExistsException(String message) {
        super(message);
    }
}
