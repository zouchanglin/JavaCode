package com.soyanga.httpd.handler;

import com.soyanga.httpd.common.Handler;
import com.soyanga.httpd.common.HttpRequest;
import com.soyanga.httpd.common.HttpResponse;
import com.soyanga.httpd.common.HttpStatus;

/**
 * @program: webServer-httpd
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-01-26 23:32
 * @Version 1.0
 */
public class MethodNotAllowedHandler implements Handler {
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        response.flush();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
