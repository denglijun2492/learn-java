package denglj.learn.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo1 {
    public static void main(String[] args) {
        //1.filter 过滤
        List<String> ss = Arrays.asList("abc", "", "dd", "wewe", "gggg");
        List<String> filtered = ss.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        System.out.println(filtered);

        //2.forEach，把方法当中参数传递
        //limit-获取指定数量的流
        new Random().ints(1, 1000).limit(10).forEach(System.out::println);

        //3.map使用
        List<Integer> numbers = Arrays.asList(3,4,5,6,4,3,5);
        List<Integer> list = numbers.stream()
                .map(i -> i * 2)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);

        //4.sorted 排序
        new Random()
                .ints(1, 100)
                .limit(8)
                .sorted()
                .forEach(System.out::println);

        //5.parallel 并行运行 打印空字符串数量
        ss = Arrays.asList("abc", "b", "dd", "", "2323", "hhh", "ppp", "ewewe", "");
        long count = ss.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println("\n"+ count);

        //6.统计
        System.out.println("--------------统计----------------");
        numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

    }
}
