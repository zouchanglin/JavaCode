package com.soyanga.httpd.core;

import com.soyanga.httpd.common.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @program: webServer-httpd
 * @Description: 服务器处理url的参数部分与url的解码
 * @Author: SOYANGA
 * @Create: 2019-01-25 22:17
 * @Version 1.0
 */
public class DispatcherHandler {

    private final Map<String, Handler> handlers = new HashMap<>();

    public void registerHandler(String url, Class<? extends Handler> classes) {
        try {
            Handler handler = classes.getConstructor().newInstance();
            handlers.put(url, handler);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param queryString url参数部分
     * @return params url参数部分的一个map
     */
    public static Map<String, List<String>> decodeParameters(String queryString) {
        Map<String, List<String>> params = new HashMap<>();
        //key1=value1&key2=value2&key1=value5;
        if (queryString != null) {
            StringTokenizer st = new StringTokenizer(queryString, "&");
            while (st.hasMoreTokens()) {
                String kv = st.nextToken();
                int index = kv.indexOf("=");
                String key = decodePercent(kv.substring(0, index));
                String value = decodePercent(kv.substring(index + 1));
                if (params.containsKey(key)) {//处理重复的参数
                    List<String> values = params.get(key);
                    values.add(value);
                } else {
                    List<String> values = new ArrayList<>();
                    values.add(value);
                    params.put(key, values);
                }
            }
        }
        return params;
    }

    /**
     * @param str 参数部分需要解码的字符串
     * @return 解码完后的字符串
     */
    public static String decodePercent(String str) {
        String decode = null;
        try {
            decode = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
//            e.printStackTrace();
        }
        return decode;
    }

    /**
     * @param value 要转换的字符串
     * @return 字节数组
     */
    public static byte[] encodeContent(String value) {
        return value.getBytes();
    }


    /**
     * 服务器核心处理业务
     *
     * @param socket
     * @return
     */
    public Runnable handler(Socket socket) {
        HttpReqRespWrapper wrapper = new HttpReqRespWrapper(socket);
        //请求和响应
        HttpRequest request = wrapper.getRequest();
        HttpResponse response = wrapper.getResponse();

        return new Runnable() {
            @Override
            public void run() {
                //先判断URL是否是静态资源
                if (isStaticResource(request.url())) {
                    Handler handler = handlers.get("_default_");
                    handler.doGet(request, response);

                } else { //是否是GET方法
                    Handler handler = handlers.get(request.url());
                    if (handler == null) {
                        //404 找不到资源
                        handlers.get("404").doGet(request, response);
                    } else if (request.method() == HttpMethod.GET) {
                        handler.doGet(request, response);
                    } else if (request.method() == HttpMethod.POST) {
                        handler.doPost(request, response);
                    } else if (HttpMethod.lookup(request.method().toString()) == null) {
                        //405 方法不支持
                        handlers.get("405").doGet(request, response);
                    }
                }

                System.out.println(
                        request.url() +
                                "客户端：" + socket.getPort() + "退出·" +
                                LocalDateTime.now().toString()
                );
            }
        };
    }

    private boolean isStaticResource(String url) {
        if (url == null) {
            return false;
        }
        int index = url.lastIndexOf(".");
        if (index == -1) {
            return false;
        } else {
            String extend = url.substring(index + 1);
            return SupportedMimeType.lookup(extend) != null;
        }
    }
}
