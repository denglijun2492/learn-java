package denglj.learn.jdk8.stream;

import denglj.learn.gson.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo2 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.stream().collect(Collectors.groupingBy(
                Person::getNation,
                Collectors.counting()
                )
        );
    }
}
