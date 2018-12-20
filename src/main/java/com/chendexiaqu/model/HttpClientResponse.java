package com.chendexiaqu.model;

import org.springframework.http.HttpStatus;

public class HttpClientResponse<T> {
    private final HttpStatus status;
    private final T body;

    public HttpClientResponse(HttpStatus status) {
        this(status, null);
    }

    public HttpClientResponse(T body) {
        this(HttpStatus.OK, body);
    }

    public HttpClientResponse(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }
}
