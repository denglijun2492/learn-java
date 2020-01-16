package denglj.demo.aop.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 17:47
 **/
public class MyCglibProxy {

    public Person createProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new MyCglibInterceptor());
        Person person = (Person)enhancer.create();
        return person;
    }
}
