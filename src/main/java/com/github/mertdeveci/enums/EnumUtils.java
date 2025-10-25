package com.github.mertdeveci.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EnumUtils {
    private static final Logger logger = LoggerFactory.getLogger(EnumUtils.class);

    private EnumUtils() {
    }

    public static <T extends Enum<T> & ValueEnum<V>, V> T getValueEnum(Class<T> enumType, V value, T defaultEnumTypeIfNotFound) {
        T t = null;
        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            t = getEnumConstantValue(enumConstant, value);
            if (t != null) {
                break;
            }
        }

        return (T)(t == null ? defaultEnumTypeIfNotFound : t);
    }

    private static <T extends Enum<T> & ValueEnum<V>, V> T getEnumConstantValue(T enumConstant, V value) {
        T t = null;
        if (value instanceof String) {
            if (((String)((ValueEnum)enumConstant).getValue()).equalsIgnoreCase((String)value)) {
                t = enumConstant;
            }
        } else if (((ValueEnum)enumConstant).getValue().equals(value)) {
            t = enumConstant;
        }

        return t;
    }

    public static <T extends Enum<T> & ValueEnum<V>, V> T getValueEnum(Class<T> enumType, V value) {
        return getValueEnum(enumType, value, null);
    }

}