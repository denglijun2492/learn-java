package denglj.demo.extension_points;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    MyBean myBean;

    @Test
    public void testBeanFactoryPostProcessor(){
        myBean.printName();
    }

    @Test
    public void testBeanPostProcessor(){
        myBean.printBindValue();
    }
}
