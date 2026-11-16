package com.github.mertdeveci.data.service;

import com.github.mertdeveci.enums.Status;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class EntityExistsService<I extends Number> {

    public abstract boolean exists(@NonNull I id, @NonNull Status status);

    public boolean absent(@NonNull I id, @NonNull Status status) {
        return !exists(id, status);
    }

    public <T> Optional<T> ifActiveThenGet(@NonNull I id, @NonNull Supplier<T> supplier) {
        if (exists(id, Status.ACTIVE)){
            return Optional.ofNullable(supplier.get());
        }
        return Optional.empty();
    }

    public <T> void ifActiveThenAccept(@NonNull I id, T t, @NonNull Consumer<T> consumer) {
        if (exists(id, Status.ACTIVE)){
            consumer.accept(t);
        }
    }

    public <T> Optional<T> ifExistsThenGet(@NonNull I id, @NonNull Status status, @NonNull Supplier<T> supplier) {
        if (exists(id, status)){
            return Optional.ofNullable(supplier.get());
        }
        return Optional.empty();
    }

    public <T> void ifExistsThenAccept(@NonNull I id, @NonNull Status status, T t, @NonNull Consumer<T> consumer) {
        if (exists(id, status)){
            consumer.accept(t);
        }
    }

    public <T> void ifAbsentThenGet(@NonNull I id, @NonNull Status status, @NonNull Supplier<T> supplier) {
        if (absent(id, status)){
            supplier.get();
        }
    }

    public <T> void ifAbsentThenAccept(@NonNull I id, Status status,@NonNull T t, @NonNull Consumer<T> consumer) {
        if (absent(id, status)){
            consumer.accept(t);
        }
    }
}
