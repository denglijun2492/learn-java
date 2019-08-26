package denglj.learn.graphql.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Optional<String> r = list.stream().filter(s -> {
            return s.equals("b");
        }).findFirst();
        System.out.println(r);
    }
}
