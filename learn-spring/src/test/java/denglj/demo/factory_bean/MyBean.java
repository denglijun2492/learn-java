package denglj.demo.factory_bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/14 17:20
 **/
@Getter
@Setter
@Slf4j
public class MyBean {
    private String id;
    private String name;
    private String desc;

    public void print(){
        log.info("{}, {}", id, name);
    }
}
