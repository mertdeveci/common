package com.github.mertdeveci.data.service;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.data.utils.EntityUtils;
import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.helper.DateHelper;
import org.springframework.lang.NonNull;

public abstract class EntityUpdateService<E extends AbstractIdStatusEntity> {

    public abstract E update(@NonNull E entity);

    public E update(@NonNull E entity, @NonNull Status status) {
        return switch (status) {
            case ACTIVE -> activate(entity);
            case PASSIVE -> passivate(entity);
            case DELETED -> delete(entity);
        };
    }

    public E activate(@NonNull E entity) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Deleted entity cannot be activated!");
        }
        entity.setStatus(Status.ACTIVE);
        entity.setUdate(DateHelper.now());
        return update(entity);
    }

    public E passivate(@NonNull E entity) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Deleted entity cannot be passivated!");
        }
        entity.setStatus(Status.PASSIVE);
        entity.setUdate(DateHelper.now());
        return update(entity);
    }

    public E delete(@NonNull E entity) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Entity is already deleted!");
        }
        entity.setStatus(Status.DELETED);
        entity.setUdate(DateHelper.now());
        return update(entity);
    }
}
