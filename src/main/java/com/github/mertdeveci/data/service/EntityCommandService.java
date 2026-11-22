package com.github.mertdeveci.data.service;

import com.github.mertdeveci.data.entity.AbstractStatusEntity;
import com.github.mertdeveci.data.repository.EntityRepository;
import com.github.mertdeveci.data.utils.EntityUtils;
import com.github.mertdeveci.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class EntityCommandService<E extends AbstractStatusEntity,
        R extends EntityRepository<E>> {

    protected final R repository;

    protected EntityCommandService(@NonNull R repository) {
        this.repository = repository;
    }

    // ---- CREATE / UPDATE ----

    public E create(@NonNull E entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("New entity must not have an id");
        }
        if (entity.getStatus() == null) {
            entity.setStatus(Status.ACTIVE);
        } else if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Entity cannot be created as DELETED");
        }
        beforeCreate(entity);
        return repository.save(entity);
    }

    public E update(@NonNull E entity) {
        beforeUpdate(entity);
        return repository.save(entity);
    }

    // ---- STATUS (entity üzerinden) ----

    public E changeStatus(@NonNull E entity, @NonNull Status target) {
        assertTransition(entity, target);
        entity.setStatus(target);
        return update(entity);
    }

    public E activate(@NonNull E entity)  { return changeStatus(entity, Status.ACTIVE); }
    public E passivate(@NonNull E entity) { return changeStatus(entity, Status.PASSIVE); }
    public E delete(@NonNull E entity)    { return changeStatus(entity, Status.DELETED); }

    // ---- STATUS (ID üzerinden: entity yüklenir, tüm JPA mekanizmaları çalışır) ----

    public E changeStatus(@NonNull Long id, @NonNull Status target) {
        return changeStatus(load(id), target);
    }

    public E activateById(@NonNull Long id)  { return changeStatus(id, Status.ACTIVE); }
    public E passivateById(@NonNull Long id) { return changeStatus(id, Status.PASSIVE); }
    public E deleteById(@NonNull Long id)    { return changeStatus(id, Status.DELETED); }

    // ---- HOOK'LAR ----

    protected E load(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found: id=" + id));
    }

    // Varsayılan kural: DELETED durumundaki entity'nin durumu değiştirilemez
    protected void assertTransition(E entity, Status target) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalStateException("Deleted entity cannot be changed to " + target);
        }
    }

    protected void beforeCreate(E entity) { }
    protected void beforeUpdate(E entity) { }
}
