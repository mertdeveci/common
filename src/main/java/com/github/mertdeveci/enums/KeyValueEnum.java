package com.github.mertdeveci.enums;

public interface KeyValueEnum <K,V> extends ValueEnum<V> {
    K getKey();
}
