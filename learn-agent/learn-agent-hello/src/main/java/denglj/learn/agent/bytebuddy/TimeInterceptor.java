package denglj.learn.agent.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class TimeInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception{
        long start = System.currentTimeMillis();
        try {
            //原函数的执行
            return callable.call();
        }finally {
            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
        }

    }
}
