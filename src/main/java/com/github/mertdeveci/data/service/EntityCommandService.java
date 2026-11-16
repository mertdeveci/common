package com.github.mertdeveci.data.service;

import com.github.mertdeveci.data.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.data.mapper.EntityMapper;
import com.github.mertdeveci.data.repository.EntityBaseRepository;
import com.github.mertdeveci.data.utils.EntityUtils;
import com.github.mertdeveci.enums.Status;
import com.github.mertdeveci.helper.DateHelper;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class EntityCommandService<E extends AbstractIdStatusEntity, V,
        R extends EntityBaseRepository<E>> {

    protected final R repository;
    protected final EntityMapper<E, V> mapper;

    protected EntityCommandService(@NonNull R repository, @NonNull EntityMapper<E, V> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // ---- CREATE / UPDATE ----
    public E create(@NonNull V vo) {
        E entity = mapper.toEntity(vo);
        beforeCreate(entity);
        return repository.save(entity);
    }

    public E update(@NonNull E entity) {
        beforeUpdate(entity);
        entity.setUdate(DateHelper.now());
        return repository.save(entity);
    }

    // ---- STATUS (yüklü entity üzerinden: lifecycle callback'leri ve @Version çalışır) ----

    public E changeStatus(@NonNull E entity, @NonNull Status target) {
        assertTransition(entity, target);
        entity.setStatus(target);
        return update(entity);
    }

    public E activate(@NonNull E entity)  { return changeStatus(entity, Status.ACTIVE); }
    public E passivate(@NonNull E entity) { return changeStatus(entity, Status.PASSIVE); }
    public E delete(@NonNull E entity)    { return changeStatus(entity, Status.DELETED); }

    // ---- STATUS (ID üzerinden, entity yüklemeden tek UPDATE) ----

    public void changeStatus(@NonNull Long id, @NonNull Status target) {
        int affected = repository.updateStatus(id, target, DateHelper.now(), Status.DELETED);
        if (affected == 0) {
            throw new EntityNotFoundException("Entity not found or already deleted: id=" + id);
        }
    }

    public void activateById(@NonNull Long id)  { changeStatus(id, Status.ACTIVE); }
    public void passivateById(@NonNull Long id) { changeStatus(id, Status.PASSIVE); }
    public void deleteById(@NonNull Long id)    { changeStatus(id, Status.DELETED); }

    // ---- HOOK'LAR ----

    // Varsayılan kural: DELETED durumundaki entity'nin durumu değiştirilemez
    protected void assertTransition(E entity, Status target) {
        if (EntityUtils.isDeleted(entity)) {
            throw new IllegalStateException("Deleted entity cannot be changed to " + target);
        }
    }

    protected void beforeCreate(E entity) { }
    protected void beforeUpdate(E entity) { }
}
