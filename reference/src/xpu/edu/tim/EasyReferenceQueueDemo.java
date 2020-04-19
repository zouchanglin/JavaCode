package xpu.edu.tim;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

public class EasyReferenceQueueDemo {
    public static void main(String[] args) {
        Object object = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);
        object = null;
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
            Reference<Object> reference = referenceQueue.remove(2000L);
            if(reference != null){
                //TODO something
                System.out.println("do something");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
