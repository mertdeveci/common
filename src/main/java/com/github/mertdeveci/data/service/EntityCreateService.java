package com.github.mertdeveci.data.service;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.data.mapper.EntityMapper;
import org.springframework.lang.NonNull;

public abstract class EntityCreateService<E extends AbstractIdStatusEntity, V> {

    public abstract E create(V vo, @NonNull EntityMapper<E, V> entityMapper);
}
