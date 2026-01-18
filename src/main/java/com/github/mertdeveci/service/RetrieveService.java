package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.EntityMapper;
import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.service.exceptions.ExceptionUtilityService;

import java.util.Optional;

public abstract class RetrieveService<E extends AbstractIdStatusEntity, V extends Record>
        implements ExceptionUtilityService {

    public abstract Optional<E> retrieveByIdAndStatus(Long id, Status status);

    public Optional<V> retrieveByIdAndStatus(Long id, Status status, EntityMapper<E, V> mapper) {
        return retrieveByIdAndStatus(id, status)
                .map(mapper::mapToVo);
    }

    public Optional<E> retrieveByIdAndStatusIsActive(Long id) {
        return retrieveByIdAndStatus(id, Status.ACTIVE);
    }

    public Optional<V> retrieveByIdAndStatusIsActive(Long id, EntityMapper<E, V> mapper) {
        return retrieveByIdAndStatus(id, Status.ACTIVE)
                .map(mapper::mapToVo);
    }

}
