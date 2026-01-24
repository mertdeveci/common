package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.VoMapper;
import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.utils.ExceptionUtils;

import java.util.Optional;

public abstract class RetrieveService<E extends AbstractIdStatusEntity, V extends Record>
        implements VoMapper<E, V>, ExceptionUtils.EntityNotFoundExceptions {

    public abstract Optional<E> retrieveByIdAndStatus(Long id, Status status);

    public Optional<E> retrieveActiveById(Long id) {
        return retrieveByIdAndStatus(id, Status.ACTIVE);
    }

    public Optional<V> retrieveActiveVoById(Long id) {
        return retrieveByIdAndStatus(id, Status.ACTIVE).map(this::mapToVo);
    }

    public Optional<V> retrieveVoByIdAndStatus(Long id, Status status) {
        return retrieveByIdAndStatus(id, status).map(this::mapToVo);
    }

}
