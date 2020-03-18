package com.soyanga.httpd.common;

/**
 * 服务器的响应报文
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