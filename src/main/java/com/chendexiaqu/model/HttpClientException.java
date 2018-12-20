package com.chendexiaqu.model;

import org.springframework.http.HttpStatus;

public class HttpClientException extends RuntimeException {
    private final HttpStatus statusCode;

    public HttpClientException(String message, Throwable cause) {
        this(message, cause, null);
    }

    public HttpClientException(String msg, Throwable cause, HttpStatus statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getStatusCodeText() {
        return statusCode.toString();
    }
}
