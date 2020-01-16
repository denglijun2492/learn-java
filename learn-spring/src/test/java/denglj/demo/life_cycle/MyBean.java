package denglj.demo.life_cycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/9 18:00
 **/
@Slf4j
public class MyBean implements InitializingBean, DisposableBean {

    private int seq = 0;
    public void init(){
        log.info("({})调用了init方法...", ++seq);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("({})调用了afterPropertiesSet方法...", ++seq);
    }

    @Override
    public void destroy() throws Exception {
        log.info("({})调用了destroy方法", ++seq);
    }

    public void hello(){
        log.info("({})调用了hello方法...", ++seq);
    }
}
