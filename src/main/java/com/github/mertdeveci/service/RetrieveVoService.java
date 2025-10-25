package com.github.mertdeveci.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.VoMapper;
import com.github.mertdeveci.enums.Status;

public abstract class RetrieveVoService<E extends AbstractIdStatusEntity, V extends Record>
        extends RetrieveService<E> {

    private final VoMapper<E, V> mapper;

    public RetrieveVoService(VoMapper<E, V> mapper) {
        this.mapper = mapper;
    }

    public V retrieveVoById(Long id) {
        return getById(id)
                .map(mapper::mapToVo)
                .orElseThrow(throwEntityNotFoundException());
    }

    public V retrieveVoById(Long id, Status status) {
        return getByIdAndStatus(id, status)
                .map(mapper::mapToVo)
                .orElseThrow(throwEntityNotFoundException());
    }

    public V findVoById(Long id) {
        return getById(id)
                .map(mapper::mapToVo)
                .orElse(null);
    }

    public V findVoById(Long id, Status status) {
        return getByIdAndStatus(id, status)
                .map(mapper::mapToVo)
                .orElseThrow(throwEntityNotFoundException());
    }
}
