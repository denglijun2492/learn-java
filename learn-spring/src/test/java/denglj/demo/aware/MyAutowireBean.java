package denglj.demo.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Title 对自己创建的类实例进行spring依赖注入
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/3 17:43
 **/
@Component
public class MyAutowireBean implements ApplicationContextAware {

    private ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void call(){
        MyBean1 mybean = new MyBean1();
        this.context.getAutowireCapableBeanFactory().autowireBean(mybean);
        mybean.call();
    }

    public static class MyBean1{
        @Autowired
        private MyBean2 mybean;
        public void call(){
            mybean.say();
        }
    }
    @Slf4j
    @Component
    public static class MyBean2{
        public void say(){
            log.info("悄悄地告诉你，成功了....");
        }
    }
}
