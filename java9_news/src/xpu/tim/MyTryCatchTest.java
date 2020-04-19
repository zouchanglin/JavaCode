package xpu.tim;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MyTryCatchTest {
    public static void main(String[] args) {
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try(reader; writer) {
            //数据读取过程..
            reader.read();
            //reader = null;   //Error 此时reader和writer是final的，不可再次赋值
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}