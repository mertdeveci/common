package com.github.mertdeveci.business;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.EntityToVoMapper;
import com.github.mertdeveci.entity.service.EntityRetrieveService;

public abstract class BusinessRetrieveService {

    private final EntityRetrieveService<?> entityRetrieveService;
    private final EntityToVoMapper<? extends AbstractIdStatusEntity, ?> voMapper;

    protected BusinessRetrieveService(EntityRetrieveService<?> entityRetrieveService,
                                      EntityToVoMapper<? extends AbstractIdStatusEntity, ?> voMapper
    ) {
        this.entityRetrieveService = entityRetrieveService;
        this.voMapper = voMapper;
    }
}
