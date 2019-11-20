package denglj.learn.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/20 14:57
 **/
public class StreamDemos {

    public static void print(String s){
        System.out.println("输出：" + s);
    }

    /**
     * 将方法当做参数，接受一个元素处理
     */
    @Test
    public void demo1(){
        List<String> list = Arrays.asList("a", "b", "c", "d");
        list.forEach(StreamDemos::print);
        System.out.println("---------------------");
        Consumer<String> methodParam = StreamDemos::print;
        list.forEach(x -> methodParam.accept(x));
    }
}
