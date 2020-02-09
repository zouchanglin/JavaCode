package xpu.edu.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 直接缓冲区和非直接缓冲区
 * 直接缓冲区：通过allocate()方法分配缓冲区
 * - 将缓冲区建立在JVM内存中
 *
 * 非直接缓冲区：通过allocateDirect()方法分配直接缓冲区
 * - 将缓冲区建立在物理内存中，提高效率
 */
public class DirectOrNotDirectBuffer {
    public static void main(String[] args) {
        // 分配直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        //判断是否是直接缓冲区
        System.out.println(byteBuffer.isDirect()); //true
    }
}
