package denglj.learn.agent.bytebuddy.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/10 17:42
 **/
public class InterceptServiceExample {

    public static void main(String[] args) {

        HelloService service = new HelloService();
        service.hello();
        update();
        service.hello();

    }

    public static void update(){
        ByteBuddyAgent.install();
        new ByteBuddy().redefine(HelloService2.class)
                .name(HelloService.class.getName())
                .make()
                .load(Thread.currentThread().getContextClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }
}
