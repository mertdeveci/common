package com.github.mertdeveci.entity;

import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.jpa.converter.StatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractStatusEntity extends AbstractEntity {

    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status;

}
