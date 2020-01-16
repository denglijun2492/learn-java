package denglj.demo.aop;

import denglj.demo.aop.anno.BusinessService;
import denglj.demo.aop.anno.LogOperationAspect;
import denglj.demo.aop.introduction.Cow;
import denglj.demo.aop.introduction.IChook;
import denglj.demo.aop.introduction.IDuck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/2 17:47
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"application.xml"})
public class SpringTests {

    @Autowired
    ApplicationContext context;
//    @Autowired
    BusinessService businessService;
//    @Autowired
    LogOperationAspect aspect;

    /**
     * 通过xml配置文件演示aop使用
     */
    @Test
    public void testIntercept(){
        ICar car = (ICar)context.getBean("proxyCar");
        car.start();
//        car.makeError();
    }

    /**
     * 通过注解的方式演示aop使用
     */
    @Test
    public void testAop(){
        businessService.doSomething("洗碗");
//        businessService.makeError();
    }

//    @Autowired
    IDuck duck;
//    @Autowired
    Cow cow;
    /**
     * aop引入示例，对某些类动态添加方法
     */
    @Test
    public void testIntroduction(){
        duck.sayGaGaGa();
        ((IChook)duck).sayWoWoWo();
        ((IChook)cow).sayWoWoWo();
    }
}
