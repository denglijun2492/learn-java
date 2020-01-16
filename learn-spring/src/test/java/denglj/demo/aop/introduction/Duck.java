package denglj.demo.aop.introduction;

import org.springframework.stereotype.Component;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 16:22
 **/
@Component
public class Duck implements IDuck {
    public void sayGaGaGa(){
        System.out.println("嘎嘎嘎嘎嘎...");
    }
}
