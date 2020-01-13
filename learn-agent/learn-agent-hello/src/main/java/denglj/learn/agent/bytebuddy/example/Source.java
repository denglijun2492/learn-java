package denglj.learn.agent.bytebuddy.example;

/**
 * 遵循最接近原则去匹配委托的方法
 **/
public class Source {
    public String hello(String name) {
        System.out.println("called hello name");
        return null;
    }
    public String hello(int i) {
        System.out.println("called hello i");
        return null;
    }
    public String hello(Object o) {
        System.out.println("called hello o");
        return null;
    }
    public void print(String s1, String s2){
        System.out.println("called print s1 s2");
    }
}
