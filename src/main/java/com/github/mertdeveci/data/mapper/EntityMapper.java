package com.github.mertdeveci.data.mapper;

import com.github.mertdeveci.data.entity.AbstractEntity;

import java.util.function.Function;

public abstract class EntityMapper<V, E extends AbstractEntity> implements Function<V, E> {
}
