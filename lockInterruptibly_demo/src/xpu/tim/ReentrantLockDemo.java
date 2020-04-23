package xpu.tim;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();//试图将t1中断执行，但并不能
        Thread.sleep(2000);
    }
}
