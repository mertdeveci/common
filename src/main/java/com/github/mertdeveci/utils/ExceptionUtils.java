package com.github.mertdeveci.utils;

import jakarta.persistence.EntityNotFoundException;

public interface ExceptionUtils {

    interface EntityNotFoundExceptions {
        String defaultExceptionMessage();

        default void throwEntityNotFoundException() throws EntityNotFoundException {
            final String message = defaultExceptionMessage();
            throwEntityNotFoundException(message);
        }

        default void throwEntityNotFoundException(String message) throws EntityNotFoundException {
            throw new EntityNotFoundException(message);
        }
    }

    interface IllegalArgumentExceptions {
        String defaultExceptionMessage();

        default void throwEntityNotFoundException() throws IllegalArgumentException {
            final String message = defaultExceptionMessage();
            throwEntityNotFoundException(message);
        }

        default void throwEntityNotFoundException(String message) throws IllegalArgumentException {
            throw new IllegalArgumentException(message);
        }
    }

}
