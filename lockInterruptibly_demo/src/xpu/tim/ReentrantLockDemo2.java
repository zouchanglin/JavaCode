package xpu.tim;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo2 {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();//试图将t1中断执行，是可以的，产生了一个InterruptedException异常
        Thread.sleep(1000000);
    }
}
