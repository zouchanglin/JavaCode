package com.soyanga.httpd.handler;

import com.soyanga.httpd.common.Handler;
import com.soyanga.httpd.common.HttpRequest;
import com.soyanga.httpd.common.HttpResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @program: webServer-httpd
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-01-26 21:43
 * @Version 1.0
 */
public class StaticHandler implements Handler {
    private String root = System.getProperty("user.dir") + File.separator + "static";


    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        //假设index.html index.html
        String url = request.url();
        File file = new File(root, url);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                response.write(Arrays.copyOf(buff, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("charset=UTF-8");
        response.flush();
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {

    }
}
