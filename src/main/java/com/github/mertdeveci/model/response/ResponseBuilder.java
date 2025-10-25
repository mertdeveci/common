package com.github.mertdeveci.model.response;

import com.github.mertdeveci.enums.ResponseStatus;
import com.github.mertdeveci.utils.DateHelper;

import static com.github.mertdeveci.enums.ResponseStatus.ERROR;
import static com.github.mertdeveci.enums.ResponseStatus.SUCCESS;

public class ResponseBuilder {

    private Builder successBuilder(){
        return builder()
                .status(SUCCESS)
                .systemTime();
    }

    private Builder errorBuilder(){
        return builder()
                .status(ERROR)
                .systemTime();
    }

    public Response success(){
        return successBuilder()
                .build();
    }

    public Response success(String message){
        return successBuilder()
                .message(message)
                .build();
    }

    public Response success(String message, String local){
        return successBuilder()
                .message(message)
                .local(local)
                .build();
    }

    public <T> Response success(T body){
        return successBuilder()
                .body(body)
                .build();
    }

    public Response error(){
        return errorBuilder()
                .build();
    }

    public Response error(String errorMessage){
        return errorBuilder()
                .message(errorMessage)
                .build();
    }

    public <T> Response error(T body){
        return errorBuilder()
                .body(body)
                .build();
    }

    public Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private final Response response;

        private Builder() {
            this.response = new Response();
        }

        public Builder status(ResponseStatus responseStatus){
            if (responseStatus == SUCCESS) {
                this.response.setSuccess(true);
            }
            return this;
        }

        public Builder message(String message){
            this.response.setMessage(message);
            return this;
        }

        public Builder errorCode(String errorCode){
            this.response.setErrorCode(errorCode);
            return this;
        }

        public Builder systemTime(){
            this.response.setSystemTime(DateHelper.now().getTime());
            return this;
        }

        public Builder local(String local){
            this.response.setLocal(local);
            return this;
        }

        public <T> Builder body(T body){
            this.response.setData(body);
            return this;
        }

        public Response build(){
            return response;
        }
    }
}
