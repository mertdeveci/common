package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.EntityMapper;
import com.github.mertdeveci.enums.Status;

import java.util.Optional;

public abstract class RetrieveService<E extends AbstractIdStatusEntity, V extends Record>
        extends AbstractEntityService {

    private final EntityMapper<E, V> mapper;

    public RetrieveService(EntityMapper<E, V> mapper) {
        this.mapper = mapper;
    }

    public abstract Optional<E> retrieveOptionalByIdAndStatus(Long id, Status status);

    public Optional<E> retrieveActiveById(Long id) {
        return retrieveOptionalByIdAndStatus(id, Status.ACTIVE);
    }

    public V retrieveVoByIdAndStatusIsActive(Long id) {
        return retrieveActiveById(id)
                .map(mapper::mapToVo)
                .orElseThrow(createEntityNotFoundException());
    }

    public V retrieveByIdAndStatus(Long id, Status status) {
        return retrieveOptionalByIdAndStatus(id, status)
                .map(mapper::mapToVo)
                .orElseThrow(createEntityNotFoundException());
    }

}
