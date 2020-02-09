package xpu.edu;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class TestNonBlockingNIO {
    @Test
    public void client() throws IOException {
        //1、获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2、切换成非阻塞模式
        socketChannel.configureBlocking(false);

        //3、分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //4、发送数据给服务端
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String next = scanner.next();
            byteBuffer.put((new Date().toString() + "\n" + next).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        scanner.close();
    }

    @Test
    public void server() throws IOException{
        //1、获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2、切换到非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //3、绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));

        //4、获取选择器
        Selector selector = Selector.open();

        //5、将通道注册到选择器,并指定监听接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6、轮询式的获取选择器上已经就绪的事件
        while(selector.select() > 0){
            //7、获取当前选择器上所有的已注册并且已就绪的事件
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while(selectionKeyIterator.hasNext()){
                //8、获取准备就绪的事件
                SelectionKey selectionKey = selectionKeyIterator.next();

                //9、判断是什么事件准备就绪
                if(selectionKey.isAcceptable()){
                    //10若接收事件就绪，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11、连接通道切换为非阻塞
                    socketChannel.configureBlocking(false);

                    //12、将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    //13、获取当前选择器上读就绪状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    //14、读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = socketChannel.read(byteBuffer)) > 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                //15、取消选择键
                selectionKeyIterator.remove();
            }
        }
    }
}
