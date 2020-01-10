package denglj.demo.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/9 18:00
 **/
@Slf4j
public class MyBean implements InitializingBean {

    public void init(){
        log.info("调用了init方法...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("调用了afterPropertiesSet方法...");
    }

    public void hello(){
        log.info("调用了hello方法...");
    }
}
