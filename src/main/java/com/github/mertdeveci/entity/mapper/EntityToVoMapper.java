package com.github.mertdeveci.entity.mapper;

import com.github.mertdeveci.entity.AbstractEntity;

public interface EntityToVoMapper<E extends AbstractEntity, V> {
    V mapToVo(E entity);
}
