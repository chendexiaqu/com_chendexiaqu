package com.chendexiaqu.model;

import org.springframework.http.HttpMethod;

import java.util.Collections;
import java.util.Map;

public class HttpClientRequest {
    private final String pathUri;
    private final Map<String, Object> parameters;
    private final HttpMethod method;
    private Map<String, String> headers;
    private Object body;

    private HttpClientRequest(String pathUri, HttpMethod method) {
        this(pathUri, Collections.<String, Object>emptyMap(), method);
    }

    public HttpClientRequest(String pathUri, Map<String, Object> parameters, HttpMethod method) {

        this.pathUri = pathUri;
        this.parameters = parameters;
        this.method = method;
    }

    public HttpClientRequest(String pathUri, HttpMethod method, Map<String, String> headers, Object body) {
        this(pathUri, method);
        this.headers = headers;
        this.body = body;
    }

    public String getPathUri() {
        return pathUri;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Object getBody() {
        return body;
    }

}

