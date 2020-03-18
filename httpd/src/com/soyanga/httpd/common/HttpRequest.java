package com.soyanga.httpd.common;

import java.util.List;
import java.util.Map;

public interface HttpRequest {
    HttpMethodEnum method();  //Http方法    --请求行

    String url(); //Http中url

    String version(); //Http version

    Map<String, String> header(); //Http 请求报头

    Map<String, List<String>> params();  //请求参数
}