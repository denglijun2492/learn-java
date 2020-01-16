package denglj.demo.extension_points;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/14 13:39
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition myBean = beanFactory.getBeanDefinition("myBean");
        System.out.println("属性值："+ myBean.getPropertyValues().toString());
        MutablePropertyValues propertyValues = myBean.getPropertyValues();
        if(propertyValues.contains("name")){
            propertyValues.add("name", "dengsy");
            propertyValues.add("desc", "邓水悦");
            System.out.println("修改了属性值...");
        }

    }
}
