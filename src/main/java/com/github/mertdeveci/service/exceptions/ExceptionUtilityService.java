package com.github.mertdeveci.service.exceptions;

import jakarta.persistence.EntityNotFoundException;

public interface ExceptionUtilityService {

    default void throwNotFoundException(String message){
        throw new EntityNotFoundException(message);
    }

    default void throwIllegalArgumentException(String message){
        throw new IllegalArgumentException(message);
    }
}
