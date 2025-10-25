package com.github.mertdeveci.enums;

public enum Status implements ValueEnum<Integer> {
    DELETED( -1),
    PASSIVE( 0),
    ACTIVE(1 );

    private final Integer value;

    Status(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
