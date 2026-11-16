package com.github.mertdeveci.data.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEmbeddedIdStatusEntity<T> extends AbstractStatusEntity {

    @EmbeddedId
    private T id;
}
