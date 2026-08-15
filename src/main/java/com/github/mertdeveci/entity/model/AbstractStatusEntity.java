package com.github.mertdeveci.entity.model;

import com.github.mertdeveci.entity.converter.StatusConverter;
import com.github.mertdeveci.enums.Status;
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

    public boolean isActive() {
        return Status.ACTIVE.equals(status);
    }

    public boolean isInactive() {
        return !isActive();
    }

    public boolean isPassive() {
        return Status.PASSIVE.equals(status);
    }

    public boolean isNotPassive() {
        return !isPassive();
    }

    public boolean isDeleted() {
        return Status.DELETED.equals(status);
    }

    public boolean isNotDeleted() {
        return !isDeleted();
    }
}
