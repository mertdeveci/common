package com.github.mertdeveci.entity.utils;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class ValidationUtils {

    public static void throwIfNull(Object object, Supplier<RuntimeException> exceptionSupplier) {
        Optional.ofNullable(object).orElseThrow(exceptionSupplier);
    }

    public static void throwIfTrue(Object object, Supplier<RuntimeException> exceptionSupplier) {
        Optional.ofNullable(object).orElseThrow(exceptionSupplier);
    }

    public static boolean test(Object o, Predicate<Object> predicate) {
        return predicate.test(o);
    }
}
