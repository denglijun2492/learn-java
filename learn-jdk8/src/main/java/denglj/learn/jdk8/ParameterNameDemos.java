package denglj.learn.jdk8;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/20 17:38
 **/
public class ParameterNameDemos {

    public void hello(String s1, String s2, Integer s3){

    }

    /**
     * jdk8新特性
     * 带-parameters参数编译，可以获取方法参数名，否则就是arg0 arg1...
     * @throws NoSuchMethodException
     */
    @Test
    public void demo1() throws NoSuchMethodException {
        Method helloMethod = ParameterNameDemos.class.getMethod("hello", String.class, String.class, Integer.class);
        for (Parameter parameter : helloMethod.getParameters()) {
            System.out.println(parameter.getName());
        }
    }
}
