package denglj.learn.proxy.jdk;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/17 13:31
 **/
public class ComponentImpl implements Component {
    @Override
    public void send(String message) {
        System.out.println("发送了消息：" + message);
    }
}
