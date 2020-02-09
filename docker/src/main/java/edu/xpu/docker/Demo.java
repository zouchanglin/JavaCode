package edu.xpu.docker;


import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        try {
            Process dir = Runtime.getRuntime().exec("cmd /c dir");
            dir.getInputStream();
            System.out.println(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
