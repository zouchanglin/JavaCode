package xpu.tim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamAPITest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");
        Optional<List<String>> optional = Optional.ofNullable(list);

        Stream<List<String>> stream = optional.stream();
        stream.flatMap(x -> x.stream()).forEach(System.out::println);
    }
    public static void main3(String[] args) {
        //原来的控制终止方式：
        Stream.iterate(1,i -> i + 1).limit(10) .forEach(System.out::println);

        //现在的终止方式：
        Stream.iterate(1,i -> i < 100,i -> i + 1) .forEach(System.out::println);
    }

    public static void main2(String[] args) {
        Stream<String> stringStream = Stream.of("AA", "BB", null);
        System.out.println(stringStream.count()); //3

        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add(null);
        System.out.println(list.stream().count()); //2

        Stream<Object> stream1 = Stream.ofNullable(null);
        System.out.println(stream1.count()); //0

        Stream<String> stream = Stream.ofNullable("hello world");
        System.out.println(stream.count()); //1
    }

    public static void main1(String[] args) {
        List<Integer> list = Arrays.asList(45, 43, 76, 87, 42, 77, 90, 73, 67, 88);
        list.stream().dropWhile(x -> x < 50)
                .forEach(System.out::println);
        System.out.println();
        list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.stream().dropWhile(x -> x < 5)
                .forEach(System.out::println);
    }
}
