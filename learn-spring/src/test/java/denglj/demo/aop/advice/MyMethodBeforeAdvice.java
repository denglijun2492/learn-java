package denglj.demo.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 10:34
 **/
@Slf4j
public class MyMethodBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        log.info("【MethodBeforeAdvice】 调用方法{}前...", method.getName());
    }
}
