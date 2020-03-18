package com.soyanga.httpd;



import com.soyanga.httpd.core.HttpSever;



/**
 * @program: webServer-httpd
 * @Description: 服务器处理器
 * @Author: SOYANGA
 * @Create: 2019-01-24 21:48
 * @Version 1.0
 */
public class Httpd {

    public static void main(String[] args) {
        HttpSever httpSever = new HttpSever();
        httpSever.start();

    }
}
