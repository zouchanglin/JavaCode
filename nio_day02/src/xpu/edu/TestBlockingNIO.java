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

/**
 * 一、使用NIO完成网络通信的三个核心：
 * 1. 通道(Channel)：负责连接
 * java.nio.channels.Channel
 * 	- SelectableChannel
 * 	- SocketChannel
 * 	- ServerSocketChannel
 * 	- DatagramChannel
 *
 * 	- Pipe.SinkChannel
 * 	- Pipe.SourceChannel
 * 	注意：FileChannel不能使用非阻塞模式！！！选择其主要监控网络Channel）
 * 2. 缓冲区(Buffer)：负责数据的存取
 * 3. 选择器(Selector)：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 */
public class TestBlockingNIO {

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

        //4、关闭通道
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

        //6、关闭通道
        fileChannel.close();
        acceptChannel.close();
        serverSocketChannel.close();
    }
}