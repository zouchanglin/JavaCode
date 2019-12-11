package xpu.demo;

public class VisibilityTest {
    private volatile boolean flag = true;

    private void refresh(){
        flag = false;
        System.out.println(Thread.currentThread().getName()+"修改flag");
    }

    private void load() {
        System.out.println(Thread.currentThread().getName()+"开始执行...");
        while (flag){
            //shortWait(500000); // 500000纳秒(500微秒)足够清除缓存了
            //shortWait(500); // 500纳秒还没有清除缓存
        }
        System.out.println(Thread.currentThread().getName()+"跳出循环：flag=" + flag);
    }

    // 纳秒级别的等待
    private static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while (start + interval >= end);
    }

    public static void main(String[] args) {
        VisibilityTest test = new VisibilityTest();
        new Thread(()->test.load(), "threadA").start();
        try {
            Thread.sleep(2000);
            new Thread(()->test.refresh(), "threadB").start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}