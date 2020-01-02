package denglj.demo.property_holder_config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PropertyHolderConfigTests {

    @Autowired
    PropertyHolderBean bean;

    @Test
    public void test(){
        bean.print();
    }
}
