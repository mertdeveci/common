package com.github.mertdeveci.data.mapper;

import com.github.mertdeveci.data.repository.projection.EntityProjection;

import java.util.function.Function;

public abstract class ProjectionMapper <T extends EntityProjection, V extends Record> implements Function<T, V> {
}
