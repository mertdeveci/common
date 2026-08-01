package com.github.mertdeveci.entity.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.mapper.EntityMapper;
import org.springframework.lang.NonNull;


public abstract class EntityCreateService<E extends AbstractIdStatusEntity, V> {

    public abstract E create(V vo, @NonNull EntityMapper<E, V> entityMapper);
}
