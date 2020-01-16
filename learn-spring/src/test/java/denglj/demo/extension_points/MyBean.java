package denglj.demo.extension_points;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/14 13:44
 **/
@Slf4j
@Getter
@Setter
public class MyBean implements BindValue{
    private String name;
    private String desc;
    private String bindValue;

    public void printName(){
        log.info("bean的属性：{},{}", name, desc);
    }

    @Override
    public void bind(String value) {
        this.bindValue = value;

    }

    public void printBindValue(){
        log.info("设置了bindValue：{}", bindValue);
    }
}
