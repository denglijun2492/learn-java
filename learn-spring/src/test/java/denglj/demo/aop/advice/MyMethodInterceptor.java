package denglj.demo.aop.advice;


import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 9:59
 **/
@Slf4j
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("【MethodInterceptor】 拦截前...");
        Object result = methodInvocation.proceed();
        log.info("【MethodInterceptor】 拦截后...");
        return result;
    }
}
