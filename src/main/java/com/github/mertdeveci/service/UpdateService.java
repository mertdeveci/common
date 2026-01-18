package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.utils.EntityUtils;
import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.service.exceptions.ExceptionUtilityService;
import com.github.mertdeveci.utils.DateHelper;

public abstract class UpdateService<T extends AbstractIdStatusEntity>
        implements ExceptionUtilityService {

    public abstract T update(T entity);

    public T update(T entity, Status status) {
        return switch (status) {
            case ACTIVE -> activate(entity);
            case PASSIVE -> passivate(entity);
            case DELETED -> delete(entity);
        };
    }

    public T activate(T entity) {
        if (EntityUtils.isDeleted(entity)) {
            throwIllegalArgumentException("illegal.argument.exception.deleted.entity.cannot.activate");
        }
        entity.setStatus(Status.ACTIVE);
        entity.setLastUpdateDate(DateHelper.now());
        return update(entity);
    }

    public T passivate(T entity) {
        if (EntityUtils.isDeleted(entity)) {
            throwIllegalArgumentException("illegal.argument.exception.passivated.entity.cannot.activate");
        }
        entity.setStatus(Status.PASSIVE);
        entity.setLastUpdateDate(DateHelper.now());
        return update(entity);
    }

    public T delete(T entity) {
        entity.setStatus(Status.DELETED);
        entity.setLastUpdateDate(DateHelper.now());
        return update(entity);
    }
}
