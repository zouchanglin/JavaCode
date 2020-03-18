package com.soyanga.httpd.common;


/**
 * @program: webServer-httpd
 * @Description:服务器的具体处理
 * @Author: SOYANGA
 * @Create: 2019-01-24 22:16
 * @Version 1.0
 */
public interface Handler {
    void doGet(HttpRequest request, HttpResponse response);

    void doPost(HttpRequest request, HttpResponse response);
}
