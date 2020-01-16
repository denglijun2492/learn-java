package denglj.demo.aop.anno;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 14:51
 **/
@Slf4j
@Service
public class BusinessService {

    public void print(){

    }

    @LogOperation(code = "001", desc = "做某件事")
    public String doSomething(String thing){
        log.info("调用方法：doSomething");
        return "完成了：" + thing;
    }

    @LogOperation(code = "002", desc = "做错事")
    public void makeError(){
        if(true){
            throw new RuntimeException("干了一件坏事.");
        }
    }
}
