package com.github.mertdeveci.entity.utils;

import com.github.mertdeveci.entity.AbstractIdStatusEntity;
import com.github.mertdeveci.entity.AbstractStatusEntity;
import com.github.mertdeveci.enums.Status;

import java.util.Objects;
import java.util.Optional;

public final class EntityUtils {

    private EntityUtils() {}

    public static boolean isStatusEqualTo(AbstractStatusEntity entity, Status status) {
        return Optional.ofNullable(entity)
                .map(AbstractStatusEntity::getStatus)
                .filter(s -> s.equals(status))
                .isPresent();
    }

    public static boolean isStatusNotEqual(AbstractStatusEntity entity, Status status) {
        return !isStatusEqualTo(entity, status);
    }

    public static boolean isInitialized(AbstractIdStatusEntity entity) {
        return Objects.nonNull(entity) && Objects.nonNull(entity.getId());
    }

    public static boolean isActive(AbstractStatusEntity entity) {
        return isStatusEqualTo(entity, Status.ACTIVE);
    }

    public static boolean isNotActive(AbstractStatusEntity entity) {
        return !isActive(entity);
    }

    public static boolean isPassive(AbstractStatusEntity entity) {
        return isStatusEqualTo(entity, Status.PASSIVE);
    }

    public static boolean isNotPassive(AbstractStatusEntity entity) {
        return !isPassive(entity);
    }

    public static boolean isDeleted(AbstractStatusEntity entity) {
        return isStatusEqualTo(entity, Status.DELETED);
    }

    public static boolean isNotDeleted(AbstractStatusEntity entity) {
        return !isDeleted(entity);
    }

}
