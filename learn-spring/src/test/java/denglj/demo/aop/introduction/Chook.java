package denglj.demo.aop.introduction;

import org.springframework.stereotype.Component;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 16:23
 **/
@Component
public class Chook implements IChook {
    public void sayWoWoWo(){
        System.out.println("喔喔喔喔喔...");
    }
}
