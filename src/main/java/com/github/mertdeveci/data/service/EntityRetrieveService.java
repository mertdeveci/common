package com.github.mertdeveci.data.service;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.data.repository.EntityBaseRepository;
import com.github.mertdeveci.enums.Status;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Transactional(readOnly = true)
public abstract class EntityRetrieveService<E extends AbstractIdStatusEntity,
        R extends EntityBaseRepository<E>> {

    protected final R repository;

    protected EntityRetrieveService(R repository) {
        this.repository = repository;
    }

    // ---- ENTITY ----

    public Optional<E> findByIdAndStatus(@NonNull Long id, @NonNull Status status) {
        return repository.findByIdAndStatus(id, status);
    }

    public Optional<E> findActiveById(@NonNull Long id) {
        return findByIdAndStatus(id, Status.ACTIVE);
    }

    public boolean existsActiveById(@NonNull Long id) {
        return repository.existsByIdAndStatus(id, Status.ACTIVE);
    }

    public <T> Optional<T> ifActiveExists(@NonNull Long id, @NonNull Supplier<? extends T> supplier) {
        return existsActiveById(id)
                ? Optional.ofNullable(supplier.get())
                : Optional.empty();
    }

    // ---- PROJECTION (DB seviyesinde, sadece gereken kolonlar) ----

    public <T> Optional<T> findByIdAndStatus(@NonNull Long id, @NonNull Status status,
                                             @NonNull Class<T> projection) {
        return repository.findByIdAndStatus(id, status, projection);
    }

    public <T> Optional<T> findActiveById(@NonNull Long id, @NonNull Class<T> projection) {
        return findByIdAndStatus(id, Status.ACTIVE, projection);
    }

    // ---- MAPPER (hesaplanan / karmaşık VO'lar için) ----

    public <V> Optional<V> findByIdAndStatus(@NonNull Long id, @NonNull Status status,
                                             @NonNull Function<? super E, ? extends V> mapper) {
        return findByIdAndStatus(id, status).map(mapper);
    }

    public <V> Optional<V> findActiveById(@NonNull Long id,
                                          @NonNull Function<? super E, ? extends V> mapper) {
        return findActiveById(id).map(mapper);
    }
}