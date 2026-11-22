package com.github.mertdeveci.data.mapper;

import com.github.mertdeveci.data.repository.projection.EntityProjection;

import java.util.function.Function;

public abstract class ProjectionMapper <V, T extends EntityProjection> implements Function<V, T> {
}
