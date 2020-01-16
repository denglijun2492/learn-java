package denglj.demo.aop.proxy;

import denglj.demo.aop.Car;
import denglj.demo.aop.ICar;
import denglj.demo.aop.proxy.cglib.MyCglibProxy;
import denglj.demo.aop.proxy.cglib.Person;
import denglj.demo.aop.proxy.jdk.MyJDKDynamicProxy;
import org.junit.Test;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 9:32
 **/
public class DynamicProxyTests {

    @Test
    public void testJDKProxy(){
        Car car = new Car();
        MyJDKDynamicProxy<ICar> myProxy = new MyJDKDynamicProxy<>(car);
        myProxy.getProxy().start();
        myProxy.getProxy().stop();
    }

    @Test
    public void testCglibProxy(){
        MyCglibProxy myProxy = new MyCglibProxy();
        Person person = myProxy.createProxy();
        person.run();
    }
}
