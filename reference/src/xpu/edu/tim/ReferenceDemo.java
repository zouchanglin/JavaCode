package xpu.edu.tim;

import java.lang.ref.*;

public class ReferenceDemo {
    public static void main(String[] args) {
        String str = new String("ABC"); //强引用
        SoftReference<String> softReference = new SoftReference<>(str); //软引用

        WeakReference<String> weakReference = new WeakReference<>(str); //弱引用

        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<>(str, referenceQueue); //虚引用
    }
}
