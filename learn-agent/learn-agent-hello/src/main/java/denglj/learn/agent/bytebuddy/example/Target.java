package denglj.learn.agent.bytebuddy.example;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/13 14:10
 **/
public class Target {
    public static String hello(String name) {
        return "Hello " + name + "!";
    }
    public static String hello(int i) {
        return Integer.toString(i);
    }
    public static String hello(Object o) {
        return o.toString();
    }
}
