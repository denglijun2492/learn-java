package denglj.demo.aop.chain;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 16:23
 **/
public class CarFactory {

    public static void main(String[] args) {
        CarFlowChain chain = new CarFlowChain();
        chain.addStep(new FrameProduce());
        chain.addStep(new ControlProduce());
        chain.addStep(new ColorProduce());
        chain.addStep(new TyreProduce());
        chain.addStep(new TestProduce());
        chain.proceed();
    }
}
