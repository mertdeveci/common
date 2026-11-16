package com.github.mertdeveci.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEmbeddedIdEntity<T> extends AbstractEntity {

    @EmbeddedId
    private T id;
}
