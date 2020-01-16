package denglj.demo.aop.proxy.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 17:48
 **/
public class MyCglibInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法调用前...");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("方法调用后...");
        return result;
    }
}
