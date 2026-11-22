package com.github.mertdeveci.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "idate", nullable = false, updatable = false)
    @CreationTimestamp
    private Date idate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "udate")
    @UpdateTimestamp
    private Date udate;
}
