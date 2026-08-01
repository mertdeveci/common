package com.github.mertdeveci.business;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.EntityMapper;
import com.github.mertdeveci.entity.service.EntityRetrieveService;

public abstract class BusinessRetrieveService {

    private final EntityRetrieveService<?> repositoryRetrieveService;
    private final EntityMapper<? extends AbstractIdStatusEntity, ?> voMapper;

    protected BusinessRetrieveService(EntityRetrieveService<?> repositoryRetrieveService,
                                      EntityMapper<? extends AbstractIdStatusEntity, ?> voMapper
    ) {
        this.repositoryRetrieveService = repositoryRetrieveService;
        this.voMapper = voMapper;
    }
}
