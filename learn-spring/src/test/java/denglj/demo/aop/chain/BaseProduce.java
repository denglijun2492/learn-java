package denglj.demo.aop.chain;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/16 16:07
 **/
public abstract class BaseProduce {

    public void execute(CarFlowChain chain){
        this.produce();
        chain.proceed();
    }

    abstract void produce();
}
