package com.soyanga.httpd.handler;

import com.soyanga.httpd.common.Handler;
import com.soyanga.httpd.common.HttpRequest;
import com.soyanga.httpd.common.HttpResponse;

import java.util.Map;
import java.util.Properties;

/**
 * @program: webServer-httpd
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-01-26 02:46
 * @Version 1.0
 */
public class InfoHandler implements Handler {
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        Properties properties = System.getProperties();
        response.write("<h1>" + "服务器的环境信息" + "<h1>");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            response.write("<h2>" + entry.getKey() + ": " + entry.getValue() + "<h2>");
        }
        response.flush();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
