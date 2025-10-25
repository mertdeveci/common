package com.github.mertdeveci.enums;

/**
 * Enum representing possible response statuses in the system.
 */
public enum ResponseStatus {
    /** Represents a successful response. */
    SUCCESS("success"),
    /** Represents an error response. */
    ERROR("error"),
    /** Represents an error response. */
    FAIL("fail");

    private final String status;

    /**
     * Constructs a {@code ResponseStatus} enum with the specified status value.
     *
     * @param status the status value associated with the enum constant
     */
    ResponseStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the status value of the response.
     *
     * @return the status as a string
     */
    public String getStatus() {
        return status;
    }
}
