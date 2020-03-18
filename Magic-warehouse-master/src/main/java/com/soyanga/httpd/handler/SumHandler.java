package com.soyanga.httpd.handler;

import com.soyanga.httpd.common.Handler;
import com.soyanga.httpd.common.HttpRequest;
import com.soyanga.httpd.common.HttpResponse;

import java.util.List;
import java.util.Map;

/**
 * @program: webServer-httpd
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-01-26 02:26
 * @Version 1.0
 */
public class SumHandler implements Handler {
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        Map<String, List<String>> params = request.params();
        Integer a = Integer.parseInt(params.get("a").get(0));
        Integer b = Integer.parseInt(params.get("b").get(0));
        Integer sum = a + b;

        response.setContentType("text/html; charset=UTF-8");

        response.write("<h1>" + String.format("%d+%d=%d", a, b, sum) + "<h1>");
        response.flush();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
