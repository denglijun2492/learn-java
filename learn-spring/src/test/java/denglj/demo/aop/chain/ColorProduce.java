package denglj.demo.aop.chain;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 16:18
 **/
public class ColorProduce extends BaseProduce {
    @Override
    void produce() {
        System.out.println("喷上颜色...");
    }
}
