package com.chendexiaqu.client;

import com.chendexiaqu.model.HttpClientException;
import com.chendexiaqu.model.HttpClientRequest;
import com.chendexiaqu.model.HttpClientResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class HttpClientImpl<R> extends HttpClient<R> {
    private static final String USER_AGENT = "cctc";

    private final String baseUri;
    private final RestOperations restOperations;

    public HttpClientImpl(String baseUri, RestOperations restOperations) {
        this.baseUri = baseUri;
        this.restOperations = restOperations;
    }

    @Override
    public HttpClientResponse<R> sendRequest(HttpClientRequest request, ParameterizedTypeReference<R> resultType)
            throws HttpClientException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestUrl(request.getPathUri()));

        Map<String, Object> parameters = request.getParameters();
        if (null != parameters) {
            for (String k : parameters.keySet()) {
                builder.queryParam(k, parameters.get(k));
            }
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> headers = request.getHeaders();
        if (null != headers) {
            for (String h : headers.keySet()) {
                httpHeaders.add(h, headers.get(h));
            }
        }
        httpHeaders.add("User-Agent", USER_AGENT);

        Object body = request.getBody();
        HttpEntity<Object> httpEntity;
        if (null != body) {
            httpEntity = new HttpEntity<>(body);
        } else {
            httpEntity = new HttpEntity<>(httpHeaders);
        }

        try {
            ResponseEntity<R> responseEntity = restOperations.exchange(builder.build().toUriString(),
                    request.getMethod(), httpEntity, resultType);

            return from(responseEntity);
        } catch (HttpStatusCodeException e) {
            throw new HttpClientException("Http request failed due to client or server error, request=" + request,
                    e, e.getStatusCode());
        } catch (ResourceAccessException e) {
            throw new HttpClientException("Failed to access resource due to IO error, request=" + request, e);
        }
    }

    private String requestUrl(String path) {
        return baseUri + path;
    }

    private <T> HttpClientResponse<T> from(ResponseEntity<T> responseEntity) {
        return new HttpClientResponse<>(responseEntity.getStatusCode(), responseEntity.getBody());
    }
}
