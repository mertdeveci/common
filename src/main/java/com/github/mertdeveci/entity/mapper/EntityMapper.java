package com.github.mertdeveci.entity.mapper;

import com.github.mertdeveci.entity.AbstractEntity;

import java.util.function.Function;

public interface EntityMapper<E extends AbstractEntity, V> extends Function<V, E> {
}
