package denglj.learn.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/17 9:09
 **/
public class JdkProxy implements InvocationHandler {

    private Object target;
    public JdkProxy(Object target){
        this.target = target;
    }

    public <T> T getProxy(){
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前.......");
        Object result = method.invoke(target, args);
        System.out.println("调用后.......");
        if(result instanceof String || result == null){
            return result;
        }else{
            return new JdkProxy(result).getProxy();
        }
    }

    public static void main(String[] args) {
        Object obj = new JdkProxy(new HelloServiceImpl()).getProxy();
//        ((HelloService)obj).hello("world");
//        ((HelloService2)obj).hello2("world");
        Component component = ((HelloService) obj).getComponent();
        component.send("8888");

    }
}
