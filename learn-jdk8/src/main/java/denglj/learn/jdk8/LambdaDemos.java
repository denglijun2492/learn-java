package denglj.learn.jdk8;

import jdk.nashorn.internal.objects.annotations.Function;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/20 15:15
 **/

public class LambdaDemos {
    static interface MyFunction{
        String getInfo(String input, String input2);
    }

    static String getInfo(String input, String input2){
        return "hello " + input + " , from static method";
    }

    /**
     * 函数式接口实现
     */
    @Test
    public void demo1(){
        //方式一：lambda表达式
        MyFunction function = (s1, s2) -> "hello " + s1 + s2;
        //方式二：方法引用
        MyFunction function1 = LambdaDemos::getInfo;

        System.out.println(function.getInfo("denglj", "111"));
        System.out.println(function1.getInfo("denglj", "222"));

    }


    /**
     * 集合中使用，处理，排序
     */
    @Test
    public void demo2(){
        //循环打印
        Arrays.asList("a", "b", "c", "d").forEach(s -> System.out.println(s));
        //可以引用类成员和局部变量
        String str = "输出：";
        Arrays.asList("a", "b", "c", "d").forEach(s -> {
            System.out.println(str + s);
        });

        //带返回值
        List<String> list = Arrays.asList("a", "b", "c", "d");
        list.sort((a, b) -> b.compareTo(a));
        System.out.println(list);

    }


    static class Car {

        public static Car create(final Supplier<Car> supplier){
            return supplier.get();
        }
        public static void collide(final Car car){
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another){
            System.out.println("Following the " + another.toString());
        }

        public void repair(){
            System.out.println("Repaired " + this.toString());
        }
    }

    /**
     * 构造函数和方法引用
     */
    @Test
    public void demo3(){
        //构造器引用
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        //静态方法引用
        cars.forEach(Car::collide);

        //类成员方法引用
        cars.forEach(Car::repair);

        //实例方法引用
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

}
