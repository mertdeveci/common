package com.github.mertdeveci.model.response;

import com.github.mertdeveci.enums.ResultStatus;

import java.util.Objects;

import static com.github.mertdeveci.enums.ResultStatus.ERROR;
import static com.github.mertdeveci.enums.ResultStatus.SUCCESS;

public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static Builder<Void> builder() {
        return new Builder<>();
    }

    // --- success ---
    public static Response<Void> success() {
        return builder().status(SUCCESS).build();
    }

    public static Response<Void> successWithMessage(String message) {
        return builder().status(SUCCESS).message(message).build();
    }

    public static <T> Response<T> successWithBody(T body) {
        return builder().status(SUCCESS).body(body).build();
    }

    // --- error ---
    public static Response<Void> error() {
        return builder().status(ERROR).build();
    }

    public static Response<Void> error(String errorCode, String message) {
        return builder().status(ERROR).errorCode(errorCode).message(message).build();
    }

    public static <T> Response<T> errorWithBody(T body) {
        return builder().status(ERROR).body(body).build();
    }

    public static final class Builder<T> {

        private ResultStatus status;
        private String message;
        private String errorCode;
        private String locale;
        private T data;

        private Builder() {
        }

        public Builder<T> status(ResultStatus status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder<T> locale(String locale) {
            this.locale = locale;
            return this;
        }

        @SuppressWarnings("unchecked")
        public <U> Builder<U> body(U body) {
            Builder<U> self = (Builder<U>) this;   // güvenli: T tipindeki tek alan aşağıda değiştiriliyor
            self.data = body;
            return self;
        }

        public Response<T> build() {
            Objects.requireNonNull(status, "Response status must be set");
            return new Response<>(
                    status == SUCCESS,
                    message,
                    errorCode,
                    locale,
                    System.currentTimeMillis(),
                    data);
        }
    }
}
