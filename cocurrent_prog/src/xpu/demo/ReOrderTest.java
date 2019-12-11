package xpu.demo;


// volatile保证有序性
public class ReOrderTest {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args)
            throws InterruptedException {
        int i = 0;
        while (true){
            i++;

            x = 0;
            y = 0;
            a = 0;
            b = 0;

            Thread thread1 = new Thread(()->{
                shortWait(20000);
                a = 1; //volatile 写，内存屏障StoreLoad
                //手动加内存屏障
                UnsafeFactory.getUnsafe().storeFence();
                x = b; //volatile 读，再普通写
            });

            Thread thread2 = new Thread(()->{
                b = 1;
                //手动加内存屏障
                UnsafeFactory.getUnsafe().storeFence();
                y = a;
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            System.out.println("第"+ i + "次(" + x + "," + y +")");
            if(x==0 && y==0){
                break;
            }
        }
    }

    // 纳秒级别的等待
    private static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while (start + interval >= end);
    }
}