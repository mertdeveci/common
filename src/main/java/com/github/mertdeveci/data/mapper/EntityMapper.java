package com.github.mertdeveci.data.mapper;

import com.github.mertdeveci.data.entity.AbstractEntity;

import java.util.function.Function;

public interface EntityMapper<E extends AbstractEntity, V> extends Function<V, E> {
    E toEntity(V vo);
}
