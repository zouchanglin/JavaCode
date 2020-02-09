package xpu.edu.nio;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区(Buffer)在Java NIO中负责数据的存取
 * 缓冲区底层就是数据，用于存储不同数据类型的数据
 * 根据数据类型的不同(boolean类型除外)，提供了相应类型的缓冲区
 *
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取一个缓冲区
 *
 * 二、缓冲区存取数据的两个核心方法：
 * put()：存入数据到缓冲区中
 * get()：获取缓冲区中的数据
 *
 *
 * 四、缓冲区中的四个核心属性
 * capacity：缓冲区中最大存储数据的容量，一旦声明不能改变
 * limit：界限，表示缓冲区中可以操作数据的大小（limit后面的数据是不能进行读写的）
 * position：位置，表示缓冲区中正在操作数据的位置
 * mark：标记，表示记录当前position的位置，可以通过reset()恢复到mark的位置
 * 0 <= mark <= position <= limit <= capacity
 */
public class TestBuffer {
    public static void main1(String[] args) {
        String str = "abcde";

        //1、分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("------------ allocate() ------------");
        PrintBufferAttribute(byteBuffer);

        //2、存入数据到缓冲区中
        byteBuffer.put(str.getBytes());
        System.out.println("------------ put() ------------");
        PrintBufferAttribute(byteBuffer);

        //3、切换成读数据的模式
        byteBuffer.flip();
        System.out.println("------------ flip() ------------");
        PrintBufferAttribute(byteBuffer);

        //4、利用get()读取缓冲区中的数据
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println("------------ get() ------------");
        PrintBufferAttribute(byteBuffer);

        //5、rewind() 可以重复读数据
        byteBuffer.rewind();
        System.out.println("------------ rewind() ------------");
        PrintBufferAttribute(byteBuffer);

        //6、clear() 清空缓冲区，但是缓冲区中的数据依然存在，处于"被遗忘"状态
        byteBuffer.clear();
        System.out.println("------------ clear() ------------");
        PrintBufferAttribute(byteBuffer);

        System.out.println((char) byteBuffer.get());
    }

    private static void PrintBufferAttribute(ByteBuffer byteBuffer) {
        System.out.println("position:" + byteBuffer.position() +
                ", limit=" + byteBuffer.limit() +
                ", capacity=" + byteBuffer.capacity());
    }

    public static void main(String[] args) {
        String str = "abcde";

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes, 0, 2);
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.position());

        //mark()：mark标记一下
        byteBuffer.mark();
        byteBuffer.get(bytes, 2 , 2);
        System.out.println(new String(bytes,2,2));
        System.out.println(byteBuffer.position());

        //reset()：恢复到mark的位置
        byteBuffer.reset();
        System.out.println(byteBuffer.position());

        //缓冲区中还有没有可以操作的数据
        if(byteBuffer.hasRemaining()){
            //如果有，看看缓冲区中还可以操作的数据的数量
            System.out.println(byteBuffer.remaining());
        }
    }
}