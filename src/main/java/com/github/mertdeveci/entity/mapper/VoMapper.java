package com.github.mertdeveci.entity.mapper;

import com.github.mertdeveci.entity.AbstractEntity;

import java.util.function.Function;

public interface VoMapper <E extends AbstractEntity, V extends Record> extends Function<E, V> {
}
