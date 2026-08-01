package com.github.mertdeveci.entity.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.utils.EntityUtils;
import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.helper.DateHelper;
import org.springframework.lang.NonNull;

public abstract class EntityUpdateService<T extends AbstractIdStatusEntity> {

    public abstract T update(@NonNull T entity);

    public T update(@NonNull T entity, @NonNull Status status) {
        return switch (status) {
            case ACTIVE -> activate(entity);
            case PASSIVE -> passivate(entity);
            case DELETED -> delete(entity);
        };
    }

    public T activate(@NonNull T entity) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Deleted entity cannot be activated!");
        }
        entity.setStatus(Status.ACTIVE);
        entity.setUdate(DateHelper.now());
        return update(entity);
    }

    public T passivate(@NonNull T entity) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Deleted entity cannot be passivated!");
        }
        entity.setStatus(Status.PASSIVE);
        entity.setUdate(DateHelper.now());
        return update(entity);
    }

    public T delete(@NonNull T entity) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Entity is already deleted!");
        }
        entity.setStatus(Status.DELETED);
        entity.setUdate(DateHelper.now());
        return update(entity);
    }
}
