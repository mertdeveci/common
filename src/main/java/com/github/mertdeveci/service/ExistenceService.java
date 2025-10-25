package com.github.mertdeveci.service;

import com.github.mertdeveci.enums.Status;

import java.util.function.Supplier;

public abstract class ExistenceService<I extends Number> {

    public abstract boolean exists(I id, Status status);

    public boolean absent(I id, Status status) {
        return !exists(id, status);
    }

    public <T> void ifExistsThen(I id, Supplier<T> supplier) {
        if (exists(id, Status.ACTIVE)){
            supplier.get();
        }
    }

    public <T> void ifAbsentThen(I id, Supplier<T> supplier) {
        if (absent(id, Status.ACTIVE)){
            supplier.get();
        }
    }
}
