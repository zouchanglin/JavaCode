package xpu.edu;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingNIO2 {

    @Test
    public void client() throws IOException {
        //1、获取通道
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1", 9898));

        //2、分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //3、读取本地文件并发送到服务端
        FileChannel fileChannel = FileChannel.open(Paths.get("a.jpg"), StandardOpenOption.READ);

        while(fileChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.shutdownOutput();

        //4、接收服务器反馈
        int len = 0;
        while((len = socketChannel.read(byteBuffer)) != -1){
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }

        //5、关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException{
        //1、获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2、绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));

        //3、获取客户端连接的通道
        SocketChannel acceptChannel = serverSocketChannel.accept();

        //4、建立缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //5、接收客户端的数据保存到本地
        FileChannel fileChannel = FileChannel.open(Paths.get("b.jpg"),
                StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE, StandardOpenOption.READ);
        while(acceptChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //6、服务端反馈
        byteBuffer.put("Hello NIO!".getBytes());
        byteBuffer.flip();
        acceptChannel.write(byteBuffer);

        //7、关闭通道
        fileChannel.close();
        acceptChannel.close();
        serverSocketChannel.close();
    }
}