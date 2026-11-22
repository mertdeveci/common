package com.github.mertdeveci.data.service;

import com.github.mertdeveci.data.entity.AbstractStatusEntity;
import com.github.mertdeveci.data.repository.EntityBaseRepository;
import com.github.mertdeveci.data.repository.projection.EntityProjection;
import com.github.mertdeveci.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public abstract class EntityQueryService<E extends AbstractStatusEntity,
        R extends EntityBaseRepository<E>> {

    protected final R repository;

    protected EntityQueryService(@NonNull R repository) {
        this.repository = repository;
    }

    // ---- ENTITY ----

    public Optional<E> find(@NonNull Long id, @NonNull Status status) {
        return repository.findByIdAndStatus(id, status);
    }

    public Optional<E> findActive(@NonNull Long id) {
        return find(id, Status.ACTIVE);
    }

    public E getActive(@NonNull Long id) {
        return findActive(id).orElseThrow(() -> notFound(id, Status.ACTIVE));
    }

    // ---- PROJECTION (DB seviyesinde, sadece gereken kolonlar) ----

    public <T extends EntityProjection> Optional<T> find(@NonNull Long id,
                                                         @NonNull Status status,
                                                         @NonNull Class<T> projection) {
        return repository.findByIdAndStatus(id, status, projection);
    }

    public <T extends EntityProjection> Optional<T> findActive(@NonNull Long id,
                                                               @NonNull Class<T> projection) {
        return find(id, Status.ACTIVE, projection);
    }

    public <T extends EntityProjection> T getActive(@NonNull Long id,
                                                    @NonNull Class<T> projection) {
        return findActive(id, projection).orElseThrow(() -> notFound(id, Status.ACTIVE));
    }

    // ---- EXISTS ----

    public boolean exists(@NonNull Long id, @NonNull Status status) {
        return repository.existsByIdAndStatus(id, status);
    }

    public boolean existsActive(@NonNull Long id) {
        return exists(id, Status.ACTIVE);
    }

    public void requireActive(@NonNull Long id) {
        if (!existsActive(id)) {
            throw notFound(id, Status.ACTIVE);
        }
    }

    // Alt sınıf kendi domain exception'ını döndürmek için override edebilir
    protected RuntimeException notFound(Long id, Status status) {
        return new EntityNotFoundException("Entity not found: id=" + id + ", status=" + status);
    }
}