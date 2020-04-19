package xpu.edu.tim;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ReferenceQueueTest {
    private static ReferenceQueue<NormalObject> rq = new ReferenceQueue<>();

    private static void checkQueue(){
        Reference<NormalObject> reference = null;
        while ((reference = (Reference<NormalObject>) rq.poll()) != null){
            if(reference != null){
                System.out.println("In queue: "+ ((NormalObjectWeakReference)reference).name);
                System.out.println("reference object: "+ reference.get());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<WeakReference<NormalObject>> weakReferenceArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            weakReferenceArrayList.add(new NormalObjectWeakReference(new NormalObject("Weak " + i), rq));
            System.out.println("Created weak:" + weakReferenceArrayList.get(i));
        }
        System.out.println("First time");
        checkQueue();
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Second time");
        checkQueue();
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Third time");
    }
}
