package denglj.demo.aop.chain;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 16:18
 **/
public class ControlProduce extends BaseProduce {
    @Override
    void produce() {
        System.out.println("装上控制系统...");
    }
}
