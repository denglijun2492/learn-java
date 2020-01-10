package denglj.demo.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/3 17:17
 **/
@Slf4j
@Component
public class MyAware implements BeanNameAware, ApplicationContextAware {
    @Override
    public void setBeanName(String name) {
        log.info("传入了beanName: {}", name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("传入了applicationContext: {}", applicationContext);
    }
}
