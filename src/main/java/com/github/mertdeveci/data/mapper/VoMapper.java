package com.github.mertdeveci.data.mapper;

import com.github.mertdeveci.data.entity.AbstractEntity;

import java.util.function.Function;

public abstract class VoMapper <E extends AbstractEntity, V extends Record> implements Function<E, V> {
}