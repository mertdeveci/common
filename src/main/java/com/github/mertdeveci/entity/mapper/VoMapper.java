package com.github.mertdeveci.entity.mapper;

import com.github.mertdeveci.entity.model.AbstractEntity;
import com.github.mertdeveci.entity.model.EntityVo;

import java.util.function.Function;

public interface VoMapper <E extends AbstractEntity, V extends EntityVo<E>> extends Function<E, V> {
}
