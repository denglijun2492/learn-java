package denglj.learn.jdk8;

import org.junit.Test;

import java.util.Optional;
import java.util.Scanner;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/20 17:46
 **/
public class OptionalDemos {

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String s = sc.nextLine();
            System.out.println(s);
        }
    }

    @Test
    public void demo(){
        String v = "";
        Optional<String> name = Optional.ofNullable(v).filter(s -> !s.isEmpty()); //可以实现null和空串的判断
        System.out.println("name is set? " + name.isPresent());
        System.out.println("name.orElseGet： " + name.orElseGet(()->"[none]"));
        System.out.println("name.orElse： " + name.orElse("[111]"));
        System.out.println("name map or else： " + name.map(s -> "hello " + s).orElse("[222]"));
    }
}
