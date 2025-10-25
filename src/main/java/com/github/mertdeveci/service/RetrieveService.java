package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.enums.Status;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class RetrieveService<E extends AbstractIdStatusEntity> {

    public abstract Optional<E> getByIdAndStatus(Long id, Status status);

    public Supplier<EntityNotFoundException> throwEntityNotFoundException() {
        return () -> new EntityNotFoundException("Entity nof found!");
    }

    public Optional<E> getById(Long id) {
        return getByIdAndStatus(id, Status.ACTIVE);
    }

    public E retrieveById(Long id) {
        return getByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(throwEntityNotFoundException());
    }

    public E retrieveById(Long id, Status status) {
        return getByIdAndStatus(id, status)
                .orElseThrow(throwEntityNotFoundException());
    }

    public E findById(Long id) {
        return getByIdAndStatus(id, Status.ACTIVE).orElse(null);
    }

    public E findById(Long id, Status status) {
        return getByIdAndStatus(id, status).orElse(null);
    }
}
