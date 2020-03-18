package com.soyanga.httpd.handler;

import com.soyanga.httpd.common.Handler;
import com.soyanga.httpd.common.HttpRequest;
import com.soyanga.httpd.common.HttpResponse;

/**
 * @program: webServer-httpd
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-01-26 01:49
 * @Version 1.0
 */
public class IndexHandler implements Handler {
    /**
     * 只处理GET方法的请求报文
     *
     * @param request
     * @param response
     */
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        response.write("<h1> Hello Http Web<h1>");
        response.flush();

    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
