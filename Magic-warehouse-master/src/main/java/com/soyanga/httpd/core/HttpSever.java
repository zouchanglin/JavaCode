package com.soyanga.httpd.core;

import com.soyanga.httpd.handler.*;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: webServer-httpd
 * @Description: 服务器端多线程处理端
 * @Author: SOYANGA
 * @Create: 2019-01-24 22:00
 * @Version 1.0
 */
public class HttpSever {
    /**
     * 固定大小线程池根据电脑的cpu核数*2得到，经验所得。固定线程池大小不应该适当，大小为电脑的cpu核数*2，线程多了，线程上个下文切换资源浪费资源，线程少了，没办法充分利用CPU
     * 线程名利用自增数起线程名
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 5,
            new ThreadFactory() {
                private final AtomicInteger count = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("Thread-Handler-" + count.getAndIncrement());
                    return thread;
                }
            }
    );

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            System.out.println("Sever start on 127.0.0.1:80,Please visit http://127.0.0.1:80/");
            //自动打开浏览器
            openLinks2("http://127.0.0.1:80/");
            DispatcherHandler dispatcherHandler = new DispatcherHandler();
            this.loadHandler(dispatcherHandler);
            while (true) {
                Socket socket = serverSocket.accept();

                //线程池
                executorService.execute(dispatcherHandler.handler(socket));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重构：在不改变代码原有功能的基础上对代码结构进行一些调整
     * Handler在这里加载
     *
     * @param dispatcherHandler
     */
    private void loadHandler(DispatcherHandler dispatcherHandler) {

        //业务的
        dispatcherHandler.registerHandler("/", IndexHandler.class);
        dispatcherHandler.registerHandler("/sum", SumHandler.class);
        dispatcherHandler.registerHandler("/info", InfoHandler.class);

        //内置的

        //静态资源
        dispatcherHandler.registerHandler("_default_", StaticHandler.class);

        //404
        dispatcherHandler.registerHandler("404", NotFoundHandler.class);
        //405
        dispatcherHandler.registerHandler("405", MethodNotAllowedHandler.class);
    }

    private void openLinks(String url) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openLinks2(String url) {
        //判断当前系统是否支持Java AWT Desktop扩展
        if (Desktop.isDesktopSupported()) {
            try {
                //创建一个URL实例
                URI url1 = URI.create(url);
                //获取当卡你系统桌面扩展
                Desktop dp = Desktop.getDesktop();
                //判断系统桌面是否支持要执行的功能
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    //获取系统的默认浏览器打开链接
                    dp.browse(url1);
                }
            } catch (NullPointerException e) {
                System.err.println("url为空");
            } catch (IOException e) {
                System.err.println("无法获取默认浏览器");
            }


        }
    }
}
