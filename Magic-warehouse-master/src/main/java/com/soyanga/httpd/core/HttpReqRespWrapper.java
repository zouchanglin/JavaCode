package com.soyanga.httpd.core;

import com.soyanga.httpd.common.HttpRequest;
import com.soyanga.httpd.common.HttpResponse;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static com.soyanga.httpd.core.DispatcherHandler.*;

/**
 * @program: webServer-httpd
 * @Description: 服务器的请求报文响应报文的处具体处理
 * @Author: SOYANGA
 * @Create: 2019-01-25 11:41
 * @Version 1.0
 */
public class HttpReqRespWrapper {

    private HttpResponse response;
    private HttpRequest request;
    private final Socket socket;

    public HttpReqRespWrapper(Socket socket) {
        this.socket = socket;
        this.parseHandler();
    }

    public HttpResponse getResponse() {
        return response;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void parseHandler() {
        Map<String, String> line = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        Map<String, List<String>> params = new HashMap<>();
        try {
            InputStream inputStream = socket.getInputStream();
            //字节输入流转换字符输入流
            InputStreamReader buffer = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(buffer);


            String lineValue = reader.readLine();
            //首行解析 GET /index HTTP/1.1

            StringTokenizer tokenizer = null;
            if (lineValue != null) {//以空格分开
                tokenizer = new StringTokenizer(lineValue);
            }

            //拆分请求行方法eg：GET，POST
            if (tokenizer != null && tokenizer.hasMoreTokens()) {
                String method = tokenizer.nextToken();
                line.put("method", method);
            }
            //GET /index?key1=value&key2=value2 HTTP/1.1
            //以问号拆分url和参数
            if (tokenizer != null && tokenizer.hasMoreTokens()) {
                String url = tokenizer.nextToken();
                int index = url.indexOf("?");

                if (index != -1) { //请求行有参数  //拆分参数和url

                    params = decodeParameters(url.substring(index + 1));
                    url = decodePercent(url.substring(0, index));
                } else {//请求行无参数，只有url
                    url = decodePercent(url);

                }
                line.put("url", url);
            }
            //拆分请求行版本号
            if (tokenizer != null && tokenizer.hasMoreTokens()) {
                String version = tokenizer.nextToken();
                line.put("version", version);
            }
            //下一行 请求头 key：value
            lineValue = reader.readLine();

            if (lineValue != null) {
                while (!(lineValue).trim().isEmpty()) {
                    int index = lineValue.indexOf(": ");
                    String key = lineValue.substring(0, index);

                    String value = lineValue.substring(index + 1);
                    header.put(key, value);
                    //换下一行
                    lineValue = reader.readLine();
                }
            }
            this.request = new DefaultHttpRequest(line, header, params);
            this.response = new DefaultHttpResponse(socket, request);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
