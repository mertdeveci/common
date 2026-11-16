package com.github.mertdeveci.data.service;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.data.repository.EntityBaseRepository;
import com.github.mertdeveci.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Transactional(readOnly = true)
public abstract class EntityQueryService<E extends AbstractIdStatusEntity,
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

    public <T> Optional<T> find(@NonNull Long id, @NonNull Status status,
                                @NonNull Class<T> projection) {
        return repository.findByIdAndStatus(id, status, projection);
    }

    public <T> Optional<T> findActive(@NonNull Long id, @NonNull Class<T> projection) {
        return find(id, Status.ACTIVE, projection);
    }

    // ---- MAPPER (hesaplanan / karmaşık VO'lar için) ----

    public <V> Optional<V> findAndMap(@NonNull Long id, @NonNull Status status,
                                      @NonNull Function<? super E, ? extends V> mapper) {
        return find(id, status).map(mapper);
    }

    public <V> Optional<V> findActiveAndMap(@NonNull Long id,
                                            @NonNull Function<? super E, ? extends V> mapper) {
        return findAndMap(id, Status.ACTIVE, mapper);
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

    // ---- KOŞULLU ÇALIŞTIRMA ----

    public <T> Optional<T> ifExists(@NonNull Long id, @NonNull Status status,
                                    @NonNull Supplier<? extends T> supplier) {
        return exists(id, status) ? Optional.ofNullable(supplier.get()) : Optional.empty();
    }

    public <T> Optional<T> ifActive(@NonNull Long id, @NonNull Supplier<? extends T> supplier) {
        return ifExists(id, Status.ACTIVE, supplier);
    }

    public void runIfExists(@NonNull Long id, @NonNull Status status, @NonNull Runnable action) {
        if (exists(id, status)) action.run();
    }

    public void runIfActive(@NonNull Long id, @NonNull Runnable action) {
        runIfExists(id, Status.ACTIVE, action);
    }

    public void runIfAbsent(@NonNull Long id, @NonNull Status status, @NonNull Runnable action) {
        if (!exists(id, status)) action.run();
    }

    // Alt sınıf kendi domain exception'ını döndürmek için override edebilir
    protected RuntimeException notFound(Long id, Status status) {
        return new EntityNotFoundException("Entity not found: id=" + id + ", status=" + status);
    }
}
