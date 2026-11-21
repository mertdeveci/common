package com.github.mertdeveci.model.response;

public record Response<T>(
        boolean success,
        String message,
        String errorCode,
        String locale,
        long systemTime,
        T data
) {
}
