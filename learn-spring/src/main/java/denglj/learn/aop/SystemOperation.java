package denglj.learn.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemOperation {
    String code() default "";
    String desc() default "";
    String template() default "";
}
