package denglj.demo.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 16:19
 **/
@Aspect
@Component
public class MyIntroduction {
    /**
     * 该示例实现：
     *  对denglj.demo.aop.introduction.* 包下bean
     *  新增了Chook的方法实现
     *  让鸭子、牛能发出鸡的声音...
     */
    @DeclareParents(value = "denglj.demo.aop.introduction.*", defaultImpl = Chook.class)
    public IChook chook;
}
