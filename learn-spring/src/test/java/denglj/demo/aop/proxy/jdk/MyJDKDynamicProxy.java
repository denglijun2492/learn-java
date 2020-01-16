package denglj.demo.aop.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 9:24
 **/
public class MyJDKDynamicProxy<T> implements InvocationHandler {

    private T target;

    public MyJDKDynamicProxy(T target){
        this.target = target;
    }

    public T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用"+ method.getName() +"前...");
        Object result = method.invoke(target, args);
        System.out.println("调用"+ method.getName() +"后...");
        return result;
    }
}
