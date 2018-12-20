package com.chendexiaqu.client;

import com.chendexiaqu.model.HttpClientException;
import com.chendexiaqu.model.HttpClientRequest;
import com.chendexiaqu.model.HttpClientResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.Map;

public abstract class HttpClient<R> {

    HttpClientResponse<R> doGet(String path, Map<String, Object> parameters, ParameterizedTypeReference<R> resultType) {
        return sendRequest(new HttpClientRequest(path, parameters, HttpMethod.GET), resultType);
    }

    HttpClientResponse<R> doPost(String path, Map<String, String> headers, Object body,
                                         ParameterizedTypeReference<R> resultType) {
        return sendRequest(new HttpClientRequest(path, HttpMethod.POST, headers, body), resultType);
    }

    abstract HttpClientResponse<R> sendRequest(HttpClientRequest request, ParameterizedTypeReference<R> resultType)
            throws HttpClientException;
}
