package com.github.mertdeveci.entity.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.VoMapper;
import com.github.mertdeveci.enums.Status;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class EntityRetrieveService<E extends AbstractIdStatusEntity> {

    // GET ENTITY
    public abstract Optional<E> findByIdAndStatus(@NonNull Long id, @NonNull Status status);

    public Optional<E> findActiveById(@NonNull Long id) {
        return findByIdAndStatus(id, Status.ACTIVE);
    }

    public <T> Optional<T> findActiveByIdThenSupply(@NonNull Long id, @NonNull Supplier<T> supplier) {
        if (findActiveById(id).isPresent()) {
            return Optional.of(supplier.get());
        }
        return Optional.empty();
    }

    public void findActiveByIdThenConsume(@NonNull Long id, @NonNull Consumer<E> consumer) {
        findActiveById(id).ifPresent(consumer);
    }

    // GET VO
    public <V extends Record> Optional<V> findByIdAndStatus(@NonNull Long id, @NonNull Status status, @NonNull VoMapper<E,V> voMapper){
        return findByIdAndStatus(id, status)
                .map(voMapper);
    }

    public <V extends Record> Optional<V> findActiveById(@NonNull Long id, @NonNull VoMapper<E,V> voMapper) {
        return findByIdAndStatus(id, Status.ACTIVE)
                .map(voMapper);
    }
}
