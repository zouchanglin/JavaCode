package com.soyanga.httpd.core;

import com.soyanga.httpd.common.HttpRequest;
import com.soyanga.httpd.common.HttpResponse;
import com.soyanga.httpd.common.HttpStatus;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.soyanga.httpd.core.DispatcherHandler.encodeContent;

/**
 * @program: webServer-httpd
 * @Description: 服务器的默认响应报文
 * @Author: SOYANGA
 * @Create: 2019-01-24 23:13
 * @Version 1.0
 */
public class DefaultHttpResponse implements HttpResponse {

    private final Socket socket;

    private final HttpRequest request;

    private HttpStatus httpStatus = HttpStatus.OK;

    private Map<String, String> header = new HashMap<>();

    private ByteArrayOutputStream content = new ByteArrayOutputStream();

    public DefaultHttpResponse(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    @Override
    public void setStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    //最重要的是头中的一个字段：支持的类型
    @Override
    public void setHeader(String key, String value) {
        this.header.put(key, value);

    }

    @Override
    public void setContentType(String value) {
        this.header.put("Content-Type", value);
    }

    /**
     * 正文通过字节流的形式写到缓冲区（内存）中
     *
     * @param value
     */
    @Override
    public void write(byte[] value) {
        try {
            content.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 正文通过字符串的形式写到缓冲（内存）去中去
     *
     * @param value
     */
    @Override
    public void write(String value) {
        try {
            content.write(value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        //HTTP1.1 200 OK  响应行
        //header1 : value1 //响应报头
        //header2 : value2
        //                 //空行
        //data             //正文
        try {
            OutputStream outputStream = socket.getOutputStream();
            //响应行
            outputStream.write(
                    encodeContent(
                            request.version()
                                    + " " +
                                    this.httpStatus.getRequestStatus()
                                    + " " +
                                    this.httpStatus.getDescription()
                                    + "\r\n"
                    )
            );
            //响应报头
            for (Map.Entry<String, String> entry : header.entrySet()) {
                outputStream.write(encodeContent(entry.getKey() + ": " + entry.getValue() + "\r\n"));
            }

            //相应空行
            outputStream.write(encodeContent("\r\n"));

            //先将正文从内存流中读取数据，然后再写到socket的输出流中去

            ByteArrayInputStream intputStream = new ByteArrayInputStream(content.toByteArray());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = intputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                System.out.println("客户端：" + socket.getPort() + "登陆" + LocalDateTime.now().toString());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
