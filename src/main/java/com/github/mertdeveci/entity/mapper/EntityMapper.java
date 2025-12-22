package com.github.mertdeveci.entity.mapper;

import com.github.mertdeveci.entity.AbstractEntity;

public interface EntityMapper<E extends AbstractEntity, V> {
    V mapToVo(E entity);
}
