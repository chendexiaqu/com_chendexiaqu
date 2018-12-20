package com.chendexiaqu.client;

import com.chendexiaqu.client.parameterizedtyperef.JsonNodeTypeRef;
import com.chendexiaqu.client.parameterizedtyperef.NiceTypeRef;
import com.chendexiaqu.model.HttpClientResponse;
import com.chendexiaqu.model.Nice;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class HttpClientImplTest {

    private HttpClientImpl httpClient;

    @Before
    public void setUp() throws Exception {
        httpClient = new HttpClientImpl("http://localhost:8080" + "", new RestTemplate());
    }

    @Test
    public void shouldTest() {
        HttpClientResponse<Nice> httpClientResponse = httpClient.doGet("", null, new NiceTypeRef());
        System.out.println("haha");
    }
}