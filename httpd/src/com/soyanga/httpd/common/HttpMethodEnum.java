package com.soyanga.httpd.common;

/**
 * 服务器的处理方法
 */
public enum HttpMethodEnum {
    GET,
    POST;
    public static HttpMethodEnum lookup(String method) {
        for (HttpMethodEnum httpMethod : HttpMethodEnum.values()) {
            if (httpMethod.name().equalsIgnoreCase(method)) {
                return httpMethod;
            }
        }
        return null;
    }
}