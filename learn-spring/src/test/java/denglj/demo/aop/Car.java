package denglj.demo.aop;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/15 9:22
 **/
public class Car implements ICar {
    @Override
    public void start() {
        System.out.println("启动车子...");
    }

    @Override
    public void stop() {
        System.out.println("停止车子...");
    }

    @Override
    public void makeError() {
        if(true){
            throw new RuntimeException("我制造了异常...");
        }
    }
}
