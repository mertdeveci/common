package com.github.mertdeveci.service;

import com.github.mertdeveci.enums.Status;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ExistsService<I extends Number> {

    public abstract boolean exists(I id, Status status);

    public boolean absent(I id, Status status) {
        return !exists(id, status);
    }

    public <T> Optional<T> ifActiveThenSupply(I id, Supplier<T> supplier) {
        if (exists(id, Status.ACTIVE)){
            return Optional.ofNullable(supplier.get());
        }
        return Optional.empty();
    }

    public <T> void ifActiveThenConsume(I id, T t, Consumer<T> consumer) {
        if (exists(id, Status.ACTIVE)){
            consumer.accept(t);
        }
    }

    public <T> Optional<T> ifExistsThenSupply(I id, Status status, Supplier<T> supplier) {
        if (exists(id, status)){
            return Optional.ofNullable(supplier.get());
        }
        return Optional.empty();
    }

    public <T> void ifExistsThenConsume(I id, Status status, T t, Consumer<T> consumer) {
        if (exists(id, status)){
            consumer.accept(t);
        }
    }

    public <T> void ifAbsentThenSupply(I id, Status status, Supplier<T> supplier) {
        if (absent(id, status)){
            supplier.get();
        }
    }

    public <T> void ifAbsentThenConsume(I id, Status status, T t, Consumer<T> consumer) {
        if (absent(id, status)){
            consumer.accept(t);
        }
    }
}
