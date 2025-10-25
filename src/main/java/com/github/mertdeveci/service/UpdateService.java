package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.utils.EntityUtils;
import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.utils.DateHelper;

public abstract class UpdateService<T extends AbstractIdStatusEntity> {

    public abstract T update(T entity);

    public T update(T entity, Status status) {
        return switch (status) {
            case ACTIVE -> activate(entity);
            case PASSIVE -> passivate(entity);
            case DELETED -> delete(entity);
        };
    }

    public T activate(T entity){
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Deleted entity cannot be activated!");
        }
        entity.setStatus(Status.ACTIVE);
        entity.setLastUpdateDate(DateHelper.now());
        return update(entity);
    }

    public T passivate(T entity){
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalArgumentException("Deleted entity cannot be passivated!");
        }
        entity.setStatus(Status.PASSIVE);
        entity.setLastUpdateDate(DateHelper.now());
        return update(entity);
    }

    public T delete(T entity){
        entity.setStatus(Status.DELETED);
        entity.setLastUpdateDate(DateHelper.now());
        return update(entity);
    }
}
