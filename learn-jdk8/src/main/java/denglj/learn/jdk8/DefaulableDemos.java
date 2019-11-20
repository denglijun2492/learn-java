package denglj.learn.jdk8;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * @Title 接口默认方法和静态方法
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/20 17:03
 **/
public class DefaulableDemos {
    interface Defaulable {
        default String hello(String message){
            return "hello : " + message;
        }

        static int max(int a, int b){
            return Math.max(a, b);
        }
    }
    class DefaulableImpl implements Defaulable {

    }
    class OverridableImpl implements Defaulable {
        @Override
        public String hello(String message) {
            return "over hello ：" + message;
        }
    }

    @Test
    public void demo1(){
        //调用静态方法
        System.out.println(Defaulable.max(2, 6));

        //调用默认实现方法
        Supplier<Defaulable> aNew = DefaulableImpl::new;
        System.out.println(aNew.get().hello("denglj"));

        //调用重写的方法
        System.out.println(new OverridableImpl().hello("denglj"));
    }
}
