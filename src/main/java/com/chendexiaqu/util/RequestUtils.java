package com.chendexiaqu.util;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RequestUtils {

    private RequestUtils() {
        //Prevent from been constructed
    }

    public static Map<String, String> getRequestHeaders(HttpServletRequest request, List<String> requiredRequestHeaders) {
        Map<String, String> requiredHeaders = new HashMap<String, String>();
        for (String k : requiredHeaders.keySet()) {
            requiredHeaders.put(k, request.getHeader(k));
        }
        return requiredHeaders;
    }

    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

}
