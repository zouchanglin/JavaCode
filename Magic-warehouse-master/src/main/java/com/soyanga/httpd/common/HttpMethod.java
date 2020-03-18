package com.soyanga.httpd.common;

/**
 * @program: webServer-httpd
 * @Description:服务器的处理方法
 * @Author: SOYANGA
 * @Create: 2019-01-24 22:23
 * @Version 1.0
 */
public enum HttpMethod {

    GET,
    POST;

    public static HttpMethod lookup(String method) {
        for (HttpMethod httpMethod : HttpMethod.values()) {
            if (httpMethod.name().equalsIgnoreCase(method)) {
                return httpMethod;
            }
        }
        return null;

    }


}
