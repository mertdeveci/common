package com.github.mertdeveci.model.response;

import com.github.mertdeveci.enums.ResponseStatus;

import java.util.Objects;
import java.util.function.Supplier;

import static com.github.mertdeveci.enums.ResponseStatus.ERROR;
import static com.github.mertdeveci.enums.ResponseStatus.SUCCESS;

public final class ResponseBuilder {

    private ResponseBuilder() {}

    public static <K extends Response> Builder<K> builder(Supplier<K> factory) {
        return new Builder<>(factory.get()).systemTime();
    }

    // --- success ---
    public static <K extends Response> K success(Supplier<K> f) {
        return builder(f).status(SUCCESS).build();
    }
    public static <K extends Response> K successWithMessage(Supplier<K> f, String message) {
        return builder(f).status(SUCCESS).message(message).build();
    }
    public static <K extends Response, T> K successWithBody(Supplier<K> f, T body) {
        return builder(f).status(SUCCESS).body(body).build();
    }

    // --- error ---
    public static <K extends Response> K error(Supplier<K> f) {
        return builder(f).status(ERROR).build();
    }
    public static <K extends Response> K error(Supplier<K> f, String errorCode, String message) {
        return builder(f).status(ERROR).errorCode(errorCode).message(message).build();
    }
    public static <K extends Response, T> K errorWithBody(Supplier<K> f, T body) {
        return builder(f).status(ERROR).body(body).build();
    }

    public static final class Builder<K extends Response> {
        private final K response;

        private Builder(K response) {
            this.response = Objects.requireNonNull(response);
        }

        public Builder<K> status(ResponseStatus status) {
            response.setSuccess(status == SUCCESS);
            return this;
        }
        public Builder<K> message(String message)     { response.setMessage(message); return this; }
        public Builder<K> errorCode(String errorCode) { response.setErrorCode(errorCode); return this; }
        public Builder<K> locale(String locale)       { response.setLocal(locale); return this; }
        public <T> Builder<K> body(T body)            { response.setData(body); return this; }
        private Builder<K> systemTime()               { response.setSystemTime(System.currentTimeMillis()); return this; }

        public K build() { return response; }
    }
}
