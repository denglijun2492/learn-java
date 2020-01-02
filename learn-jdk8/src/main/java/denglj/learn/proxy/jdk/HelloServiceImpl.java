package denglj.learn.proxy.jdk;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/17 8:45
 **/
public class HelloServiceImpl implements HelloService,HelloService2 {

    @Override
    public Component getComponent() {
        return new ComponentImpl();
    }

    @Override
    public String hello(String message) {
        System.out.println("called hello1...");
        return "receive message：" + message;
    }

    @Override
    public String hello2(String message) {
        System.out.println("called hello2...");
        return "receive message：" + message;
    }
}
