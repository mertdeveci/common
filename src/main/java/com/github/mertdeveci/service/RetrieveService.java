package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.enums.Status;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class RetrieveService<E extends AbstractIdStatusEntity> {

    public abstract Optional<E> findByIdAndStatus(Long id, Status status);

    public Optional<E> findActiveById(Long id) {
        return findByIdAndStatus(id, Status.ACTIVE);
    }

    public <T> Optional<T> findActiveByIdThenSupply(Long id, Supplier<T> supplier) {
        if (findActiveById(id).isPresent()) {
            return Optional.ofNullable(supplier.get());
        }
        return Optional.empty();
    }

    public void findActiveByIdThenConsume(Long id, Consumer<E> consumer) {
        findActiveById(id).ifPresent(consumer);
    }
}
