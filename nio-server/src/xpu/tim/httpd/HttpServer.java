package xpu.tim.httpd;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class HttpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            Socket accept = serverSocket.accept();
            byte[] bytes = new byte[1024 * 10];
            int read;
            while((read = accept.getInputStream().read(bytes)) != -1){
                String s = new String(bytes);
                System.out.println("数据" + s +"---");
            }
            ServerSocketChannel channel = serverSocket.getChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
