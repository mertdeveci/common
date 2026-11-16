package com.github.mertdeveci.data.repository;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EntityBaseRepository<E extends AbstractIdStatusEntity>
        extends JpaRepository<E, Long> {

    Optional<E> findByIdAndStatus(Long id, Status status);

    boolean existsByIdAndStatus(Long id, Status status);

    // Dinamik projection: sadece projection tipindeki alanlar SELECT edilir
    <T> Optional<T> findByIdAndStatus(Long id, Status status, Class<T> type);

    // redundant
    @Override default void deleteById(Long id)                   { throw hardDelete(); }
    @Override default void delete(E entity)                      { throw hardDelete(); }
    @Override default void deleteAllById(Iterable<? extends Long> ids) { throw hardDelete(); }
    @Override default void deleteAll(Iterable<? extends E> entities)   { throw hardDelete(); }
    @Override default void deleteAll()                           { throw hardDelete(); }
    @Override default void deleteAllInBatch()                    { throw hardDelete(); }
    @Override default void deleteAllInBatch(Iterable<E> entities){ throw hardDelete(); }
    @Override default void deleteAllByIdInBatch(Iterable<Long> ids) { throw hardDelete(); }

    private static UnsupportedOperationException hardDelete() {
        return new UnsupportedOperationException("Hard delete is not allowed, use soft delete");
    }
}
