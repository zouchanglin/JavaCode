package xpu.tim;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class VarTest {

    public static void main(String[] args) {


    }
    public static void main4(String[] args) {
        ClassLoader loader = VarTest.class.getClassLoader();
        try (var resourceAsStream = loader.getResourceAsStream("myFile.txt")){
            FileOutputStream outputStream = new FileOutputStream("myFile2.txt");
            assert resourceAsStream != null;
            resourceAsStream.transferTo(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main3(String[] args) {
        Consumer<String> consumer = (@Deprecated var t) -> System.out.println(t.toUpperCase());
        consumer.accept("tim");
    }
    public static void main2(String[] args) {
        // 判断字符串是否为空白
        " ".isBlank(); // true
        // 去除首尾空白
        " Javastack ".strip(); // "Javastack"
        // 去除尾部空格
        " Javastack ".stripTrailing(); // " Javastack"
        // 去除首部空格
        " Javastack ".stripLeading(); // "Javastack "
        // 复制字符串
        "Java".repeat(3);// "JavaJavaJava"
        // 行数统计
        "A\nB\nC".lines().count(); // 3
    }
}
