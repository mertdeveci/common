package com.github.mertdeveci.data.repository;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

@NoRepositoryBean
public interface EntityBaseRepository<E extends AbstractIdStatusEntity>
        extends JpaRepository<E, Long> {

    Optional<E> findByIdAndStatus(Long id, Status status);

    boolean existsByIdAndStatus(Long id, Status status);

    // Dinamik projection: sadece projection tipindeki alanlar SELECT edilir
    <T> Optional<T> findByIdAndStatus(Long id, Status status, Class<T> type);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
           update #{#entityName} e
              set e.status = :status, e.udate = :udate
            where e.id = :id and e.status <> :excluded
           """)
    int updateStatus(@Param("id") Long id,
                     @Param("status") Status status,
                     @Param("udate") Date udate,
                     @Param("excluded") Status excluded);
}
