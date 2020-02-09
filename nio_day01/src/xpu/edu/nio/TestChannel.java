package xpu.edu.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.SortedMap;

/**
 * Channel 用于源节点与目标节点的连接
 * 在JavaNIO中负责缓冲区数据的传输，本身不存储数据
 * 所以需要配置缓冲区进行数据传输（铁路配合火车）
 *
 * 一、通道的主要实现类
 *  java.nio.channels.Channel接口
 *      - FileChannel 文件IO
 *      - SocketChannel TCP的IO
 *      - ServerSocketChannel TCP的IO
 *      - DatagramChannel UDP的IO
 * 二、获取通道
 * 1、对支持通道的类提供了 getChannel()
 *      - 本地IO
 *      - FileInputStream/FileOutputStream
 *      - RandomAccessFile
 *
 *      - 网络IO
 *      - Socket
 *      - ServerSocket
 *      - DatagramChannel
 * 2、JDK1.7中的NIO.2针对各种通道提供了静态方法
 * 3、JDK1.7中的NIO.2的Files工具类的newByteChannel()
 */
public class TestChannel {

public static void main(String[] args) throws CharacterCodingException {
    //查看可用字符集
    SortedMap<String, Charset> charsetSortedMap = Charset.availableCharsets();
    Set<String> keySet = charsetSortedMap.keySet();
    for (String key: keySet){
        System.out.println(key+"="+charsetSortedMap.get(key));
    }

    //获取字符集
    Charset gbk = Charset.forName("GBK");

    //获取编码器
    CharsetEncoder charsetEncoder = gbk.newEncoder();
    //获取解码器
    CharsetDecoder charsetDecoder = gbk.newDecoder();

    CharBuffer charBuffer = CharBuffer.allocate(1024);

    charBuffer.put("测试字符");
    charBuffer.flip();

    //编码(CharBuffer->ByteBuffer)
    ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);
    for (int i = 0; i < byteBuffer.limit(); i++) {
        System.out.println(byteBuffer.get());
    }
    //解码(ByteBuffer->CharBuffer)
    byteBuffer.flip();
    CharBuffer charBufferResult = charsetDecoder.decode(byteBuffer);
    System.out.println(charBufferResult);
}

    public static void main05(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        //1、获取通道
        FileChannel fileChannel = randomAccessFile.getChannel();

        //2、分配指定大小的缓冲区
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);

        //3、分散读取
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        fileChannel.read(byteBuffers);

        //4、变成读模式
        for(ByteBuffer byteBuffer: byteBuffers) byteBuffer.flip();

        System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
        System.out.println("-----------------");
        System.out.println(new String(byteBuffers[1].array(), 0, byteBuffers[0].limit()));

        //5、聚集写入
        RandomAccessFile writeAccessFile = new RandomAccessFile("2.txt", "rw");
        FileChannel writeAccessFileChannel = writeAccessFile.getChannel();
        writeAccessFileChannel.write(byteBuffers);
    }

    //通道之间直接数据传输（无缓冲区存在）
    public static void main04(String[] args) throws IOException{ //704
        long start = System.currentTimeMillis();
        FileChannel readChannel = FileChannel.open(Paths.get("D:\\download\\nio\\nio.zip"), StandardOpenOption.READ);
        FileChannel writeChannel = FileChannel.open(Paths.get("D:\\download\\nio\\nio2.zip"),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

        //readChannel.transferTo(0, readChannel.size(), writeChannel);
        writeChannel.transferFrom(readChannel, 0, readChannel.size());
        readChannel.close();
        writeChannel.close();

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //利用直接缓冲区完成文件复制(内存映射文件的方式)
    public static void main03(String[] args) { //1581 1560 1523
        long start = System.currentTimeMillis();
        try {
            FileChannel readChannel = FileChannel.open(Paths.get("D:\\download\\nio\\nio.zip"), StandardOpenOption.READ);
            FileChannel writeChannel = FileChannel.open(Paths.get("D:\\download\\nio\\nio2.zip"),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

            //内存映射对象
            MappedByteBuffer readByteBuffer = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());
            MappedByteBuffer writeByteBuffer = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, readChannel.size());

            //直接对缓冲区进行数据操作
            byte[] bytes = new byte[readByteBuffer.limit()];

            readByteBuffer.get(bytes);
            writeByteBuffer.put(bytes);

            readChannel.close();
            writeChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end -start);
    }

    //通道+直接缓冲区完成文件复制
    public static void main02(String[] args) { //5439 //5302
        long start = System.currentTimeMillis();
        try {
            //1、打开通道
            FileChannel readChannel = FileChannel.open(Paths.get("D:\\download\\nio\\nio.zip"),
                    StandardOpenOption.READ);
            //StandardOpenOption的CREATE_NEW代表如果已存在则创建失败；CREATE代表如果已存在则覆盖
            FileChannel writeChannel = FileChannel.open(Paths.get("D:\\download\\nio\\nio2.zip"),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

            //2、新建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            //3、从通道中读取到缓冲区
            while (readChannel.read(byteBuffer) != -1){
                //4、把缓冲区变成读模式
                byteBuffer.flip();
                //5、写入通道
                writeChannel.write(byteBuffer);
                //6、清空缓冲区
                byteBuffer.clear();
            }
            //7、关闭通道
            readChannel.close();
            writeChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end -start);
    }

    //利用通道+非直接缓冲区完成文件复制
    public static void main01(String[] args) { //5956 5645
        long start = System.currentTimeMillis();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("D:\\download\\nio\\nio.zip"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\download\\nio\\nio2.zip"));

            //获取通道
            FileChannel inputStreamChannel = fileInputStream.getChannel();
            FileChannel outputStreamChannel = fileOutputStream.getChannel();

            //分配缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            //将通道中的数据存入缓冲区
            while ((inputStreamChannel.read(byteBuffer)) != -1){
                //变为读模式
                byteBuffer.flip();
                //将缓冲区的数据写入通道
                outputStreamChannel.write(byteBuffer);
                //清空缓冲区
                byteBuffer.clear();
            }

            //关闭通道
            outputStreamChannel.close();
            inputStreamChannel.close();

            //关闭流
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
