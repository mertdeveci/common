package com.github.mertdeveci.data.repository;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.data.repository.projection.EntityProjection;
import com.github.mertdeveci.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EntityBaseRepository<E extends AbstractIdStatusEntity>
        extends JpaRepository<E, Long> {

    Optional<E> findByIdAndStatus(Long id, Status status);

    boolean existsByIdAndStatus(Long id, Status status);

    <T extends EntityProjection> Optional<T> findByIdAndStatus(Long id, Status status, Class<T> type);
}
