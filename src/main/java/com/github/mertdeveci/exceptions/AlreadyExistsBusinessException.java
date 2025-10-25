package com.github.mertdeveci.exceptions;

public class AlreadyExistsBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AlreadyExistsBusinessException(String message) {
        super(message);
    }
}
