package com.github.mertdeveci.data.mapper;

import com.github.mertdeveci.data.entity.AbstractEntity;

import java.util.function.Function;

public interface VoMapper <E extends AbstractEntity, V extends Record> extends Function<E, V> {
}
