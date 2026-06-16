package com.github.mertdeveci.entity.service;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;

public abstract class EntityCreateService<E extends AbstractIdStatusEntity, V> {

    public abstract E create(V vo);
}
