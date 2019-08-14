package denglj.learn.aop;

import denglj.learn.freemarker.FreeMarkerEvaluate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class SystemOperationAspect {

    @Autowired
    private FreeMarkerEvaluate freeMarkerEvaluate;

    @Pointcut("@annotation(denglj.learn.aop.SystemOperation)")
    public void pointcut(){
    }

    @Before("pointcut()")
    public void before(JoinPoint point){
        log.info("-------------------before前置拦截-------------------");
        log.info("参数：{}", point.getArgs());
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {

            log.info("-------------------around 环绕拦截 -------------------");
            MethodSignature signature = (MethodSignature)point.getSignature();
            SystemOperation annotation = signature.getMethod().getAnnotation(SystemOperation.class);

            //模板匹配
            Map<String, Object> varibles = new HashMap();
            for (int i = 0; i < signature.getParameterNames().length; i++) {
                String parameterName = signature.getParameterNames()[i];
                Object parameter = point.getArgs()[i];
                Class parameterType = signature.getParameterTypes()[i];
                log.info(parameterName + " " + parameterType);
                varibles.put(parameterName, parameter);
            }
            if(!StringUtils.isEmpty(annotation.template())){
                String s = freeMarkerEvaluate.evaluate(annotation.template(), varibles);
                log.info(" 模板内容：{}", s);
            }

            log.info("调用类：{}", point.getTarget().getClass().getName());
            log.info("调用方法：{}", signature.getMethod().getName());
            log.info("方法参数：{}", point.getArgs());
            log.info("注解参数：{},{}...", annotation.code(), annotation.desc());
            //模拟权限校验
            if(annotation.code().equals("004")){
                throw new RuntimeException(String.format("调用%s,%s，无此权限....", annotation.code(), annotation.desc()));
            }

            Object result = point.proceed();
            log.info("调用成功...");
            return result;
        } catch (Throwable throwable) {
            log.info("调用失败...");
            throw throwable;
        }
    }
    @After("pointcut()")
    public void after(JoinPoint point){
        log.info("-------------------after后置拦截-------------------");
        log.info("参数：{}", point.getArgs());
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint point){
        log.info("-------------------afterReturning返回拦截-------------------");
        log.info("参数：{}", point.getArgs());
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint point){
        log.info("-------------------afterThrowing异常拦截-------------------");
        log.info("参数：{}", point.getArgs());
    }
}
