package denglj.demo.factory_bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean好处：
 * 1. 可通过编码的方法创建bean
 * 2. 隐藏创建bean复杂细节
 **/
@Slf4j
public class MyFactoryBean implements FactoryBean<MyBean> {
    /**
     * 生成bean的实例
     * @return
     * @throws Exception
     */
    @Override
    public MyBean getObject() throws Exception {
        MyBean bean = new MyBean();
        bean.setId(System.currentTimeMillis()+"");
        bean.setName("denglj");
        return bean;
    }
    /**
     * 返回bean的类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }
    /**
     * 返回是单例还是原型对象
     * 单例会放入spring容器缓存中
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }

    public void print(){
        log.info("这是MyFactoryBean类型...");
    }
}
