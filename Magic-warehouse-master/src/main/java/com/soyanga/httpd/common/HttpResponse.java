package com.soyanga.httpd.common;

/**
 * @program: webServer-httpd
 * @Description: 服务器的响应报文
 * @Author: SOYANGA
 * @Create: 2019-01-24 22:20
 * @Version 1.0
 */
public interface HttpResponse {

    //响应行
    void setStatus(HttpStatus httpStatus);

    //响应报头
    void setHeader(String key, String value);

    void setContentType(String value);

    void write(byte[] value);

    void write(String value);

    void flush();

}
