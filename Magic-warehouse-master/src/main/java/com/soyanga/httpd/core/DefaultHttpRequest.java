package com.soyanga.httpd.core;

import com.soyanga.httpd.common.HttpMethod;
import com.soyanga.httpd.common.HttpRequest;

import java.util.List;
import java.util.Map;

/**
 * @program: webServer-httpd
 * @Description: 服务器的默认请求报文
 * @Author: SOYANGA
 * @Create: 2019-01-24 23:11
 * @Version 1.0
 */
public class DefaultHttpRequest implements HttpRequest {

    //首行
    //header
    //OP

    private final Map<String, String> line;

    private final Map<String, String> header;

    private final Map<String, List<String>> params;

    public DefaultHttpRequest(Map<String, String> line, Map<String, String> header, Map<String, List<String>> params) {
        this.line = line;
        this.header = header;
        this.params = params;
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.lookup(line.get("method"));
    }

    @Override
    public String url() {
        return line.get("url");
    }

    @Override
    public String version() {

        String version = line.get("version");
        return version == null ? "HTTP/1.1" : version;
    }

    @Override
    public Map<String, String> header() {
        return header;
    }

    @Override
    public Map<String, List<String>> params() {
        return params;
    }
}
