package denglj.demo.factory_bean;

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
 * @Date 2020/1/14 13:38
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"application.xml"})
public class SpringTests {

    @Autowired
    ApplicationContext context;

    @Test
    public void testFactoryBean() {
        //由于是原型对象，每次取到的bean实例都是新创建
        MyBean bean1 = (MyBean) context.getBean("myFactoryBean");
        bean1.print();
        MyBean bean2 = (MyBean) context.getBean("myFactoryBean");
        bean2.print();
        MyBean bean3 = (MyBean) context.getBean("myFactoryBean");
        bean3.print();
        //如果要获取factoryBean本身，需加上&符号
        MyFactoryBean factoryBean = (MyFactoryBean)context.getBean("&myFactoryBean");
        factoryBean.print();
    }

}
