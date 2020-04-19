import java.util.Random;
import java.util.UUID;

public class PermGenErrTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            getRandomString(1000000).intern();
        }
        System.out.println("success");
    }

    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            stringBuilder.append(str.charAt(num));
        }
        return stringBuilder.toString();
    }
}
