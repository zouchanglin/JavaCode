package xpu.demo;
public class AtomTest {
    private static volatile int sum = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    sum++;
                }
            }).start();
        }
        System.out.println(sum);
    }
}
