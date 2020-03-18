package com.soyanga.httpd.common;

import java.util.List;
import java.util.Map;

/**
 * @program: webServer-httpd
 * @Description: 服务器的请求报文
 * @Author: SOYANGA
 * @Create: 2019-01-24 22:21
 * @Version 1.0
 */
public interface HttpRequest {

    HttpMethod method();  //Http方法    --请求行

    String url(); //Http 中url

    String version(); //Http version

    Map<String, String> header(); //Http 请求报头

    Map<String, List<String>> params();  //请求参数

}
