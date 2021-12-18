package com.marbok.rdbms.exception;

public class WriteNotAllowedException extends RuntimeException {
    public WriteNotAllowedException(String message) {
        super(message);
    }
}
