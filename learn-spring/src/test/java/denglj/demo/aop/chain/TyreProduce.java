package denglj.demo.aop.chain;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 16:18
 **/
public class TyreProduce extends BaseProduce {
    @Override
    void produce() {
        System.out.println("装上轮胎...");
    }
}
