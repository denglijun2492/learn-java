package denglj.demo.aop.anno;

import java.lang.annotation.*;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 14:32
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperation {
    String code() default "";
    String desc() default "";
}
