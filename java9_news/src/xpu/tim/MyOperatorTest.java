package xpu.tim;

import java.util.*;

public class MyOperatorTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>(){
            @Override
            public int size() {
                return super.size() * 100;
            }
        };
        set.addAll(Arrays.asList("AAA", "BB", "CCC"));
        System.out.println(set.size()); // 300
    }
}