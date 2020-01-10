package denglj.demo.aware;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/3 17:20
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class AwareTests {
    @Test
    public void test(){
      log.info("系统测试....");
    }

    @Autowired
    private MyAutowireBean autowireBean;
    @Test
    public void testAutowire(){
        autowireBean.call();
    }
}

