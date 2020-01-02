package denglj.learn.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/24 21:59
 **/
public class CarService {

    public void start(){
        System.out.println("start car...");
    }

    public void stop(){
        System.out.println("stop car...");
    }

    public static void main(String[] args) {
        CarService carService = new CarService();
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(carService.getClass().getClassLoader());
        enhancer.setSuperclass(carService.getClass());
//        enhancer.setCallback(new MethodInterceptor(){
//            @Override
//            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//                System.out.println("事务开始...");
////                Object r = methodProxy.invokeSuper(obj, args);
//                Object r = method.invoke(carService, args);
//                System.out.println("事务结束...");
//                return r;
//            }
//        });
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("事务开始...");
                Object r = method.invoke(carService,objects);
                System.out.println("事务结束...");
                return r;
            }
        });
        CarService proxy = (CarService)enhancer.create();
        proxy.start();
        proxy.stop();
    }
}
