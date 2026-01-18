package com.github.mertdeveci.service;

import com.github.mertdeveci.enums.Status;

import java.util.function.Supplier;

public abstract class ExistsService<I extends Number> {

    public abstract boolean exists(I id);

    public abstract boolean exists(I id, Status status);

    public boolean absent(I id) {
        return !exists(id);
    }

    public boolean absent(I id, Status status) {
        return !exists(id, status);
    }

    public <T> void ifActiveThen(I id, Supplier<T> supplier) {
        if (exists(id)){
            supplier.get();
        }
    }

    public <T> void ifActiveThen(I id, Status status, Supplier<T> supplier) {
        if (exists(id, status)){
            supplier.get();
        }
    }

    public <T> void ifAbsentThen(I id, Supplier<T> supplier) {
        if (absent(id, Status.ACTIVE)){
            supplier.get();
        }
    }

    public <T> void ifAbsentThen(I id, Status status, Supplier<T> supplier) {
        if (absent(id, status)){
            supplier.get();
        }
    }
}
