package com.github.mertdeveci.enums;

import lombok.Getter;

@Getter
public enum ResultStatus {
    SUCCESS("success"),
    ERROR("error"),
    FAIL("fail");

    private final String status;

    ResultStatus(String status) {
        this.status = status;
    }
}
