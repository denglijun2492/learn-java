package denglj.demo.aop.anno;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 14:36
 **/
@Aspect
@Component
@Slf4j
public class LogOperationAspect {

    private int seq = 0;

    /**
     * 匹配切入点定义
     */
    @Pointcut("@annotation(denglj.demo.aop.anno.LogOperation)")
    public void pointcut(){

    }

    /**
     * 前置通知，在方法调用前执行
     * @param point
     */
    @Before("pointcut()")
    public void before(JoinPoint point){
        int i = seq++;
        log.info("{}【Before】前置拦截...", i);
        log.info("{}【Before】参数：{}", i, point.getArgs());
    }

    /**
     * 环绕通知，在方法执行前后都会执行
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        int i = seq++;
        MethodSignature signature = (MethodSignature)point.getSignature();
        LogOperation operation = signature.getMethod().getAnnotation(LogOperation.class);
        log.info("{}【Around】环绕拦截", i);
        log.info("{}【Around】调用类：{}",i, point.getTarget().getClass().getName());
        log.info("{}【Around】调用方法：{}",i, signature.getMethod().getName());
        log.info("{}【Around】方法参数：{}", i,point.getArgs());
        log.info("{}【Around】注解参数：{}, {}",i, operation.code(), operation.desc());
        Object result = point.proceed();
        log.info("{}【Around】调用成功",i);
        return result;
    }

    /**
     * 后置通知，在方法执行后
     * @param point
     */
    @After("pointcut()")
    public void after(JoinPoint point){
        int i = seq++;
        log.info("{}【After】后置拦截...", i);
    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        int i = seq++;
        log.info("{}【AfterReturning】后置拦截...", i);
    }

    /**
     * 异常通知，在抛出异常时执行
     */
    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        int i = seq++;
        log.info("{}【AfterThrowing】调用异常...", i);
    }
}
