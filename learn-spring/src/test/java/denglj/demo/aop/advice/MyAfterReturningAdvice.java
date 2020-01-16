package denglj.demo.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 10:36
 **/
@Slf4j
public class MyAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        log.info("【AfterReturningAdvice】 调用方法{}执行后...", method.getName());
    }
}
