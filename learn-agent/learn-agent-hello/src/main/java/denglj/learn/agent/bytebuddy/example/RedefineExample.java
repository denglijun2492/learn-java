package denglj.learn.agent.bytebuddy.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/13 13:45
 **/
public class RedefineExample {

    public static class Foo{
        public void print(){
            System.out.println("this is foo...");
        }
    }
    public static class Bar{
        public void print(){
            System.out.println("this is bar...");
        }
    }

    /**
     * 重新加载类
     * ■ 类重新载入后，方法和字段不能少，可以增加
     * ■ 不支持具有静态初始化块的类
     *
     */
    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.print();
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Bar.class)
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        foo.print();
    }
}
