package denglj.demo.property_holder_config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/2 17:48
 **/
@Getter
@Setter
@Slf4j
public class PropertyHolderBean {
    private String url;
    private String driverClass;
    private String userName;
    private String password;

    public void print(){
        log.info("读取到url：{}", url);
        log.info("读取到driverClass：{}", driverClass);
        log.info("读取到userName：{}", userName);
        log.info("读取到password：{}", password);
    }
}
