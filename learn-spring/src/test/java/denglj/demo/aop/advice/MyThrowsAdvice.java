package denglj.demo.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 10:38
 **/
@Slf4j
public class MyThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception ex){
        log.info("调用发生了异常： {}", ex.getMessage());
    }
}
