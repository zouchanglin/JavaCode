package com.soyanga.httpd.common;

/**
 * 服务器的具体处理
 */
public interface Handler {
    void doGet(HttpRequest request, HttpResponse response);

    void doPost(HttpRequest request, HttpResponse response);
}