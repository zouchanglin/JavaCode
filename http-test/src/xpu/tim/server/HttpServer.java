package xpu.tim.server;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(80);
        Socket accept = serverSocket.accept();
        OutputStream outputStream = accept.getOutputStream();
        Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        writer.write("HTTP/1.0 200 OK\r\n");
        writer.write("Set-Cookie:CookieName=HelloCookie\r\n");
        writer.write("\r\n");
        writer.write("<h1>Hello, Http Server<h1>");
        writer.flush();
        writer.close();
        outputStream.close();
        serverSocket.close();
    }
}
