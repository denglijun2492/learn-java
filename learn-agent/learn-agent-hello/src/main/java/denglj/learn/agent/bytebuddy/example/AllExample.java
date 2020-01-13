package denglj.learn.agent.bytebuddy.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.concurrent.Callable;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/13 13:45
 **/
public class AllExample {
    /**
     * 方法拦截，有异常
     * @throws Exception
     */
    public static void intercept() throws Exception{
        ByteBuddyAgent.install();
        Class<? extends Foo> loaded = new ByteBuddy()
                .subclass(Foo.class)
                .method(ElementMatchers.named("message")).intercept(FixedValue.value("foo 1234."))
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader())
                .getLoaded();
        String s = (String)loaded.getMethod("message").invoke(loaded.newInstance());
        System.out.println(s);
    }

    /**
     * 委托调用，可以匹配最合适的方法调用
     * @throws Exception
     */
    public static void delegation() throws Exception {
        String helloWorld = new ByteBuddy()
                .subclass(Source.class)
                .method(ElementMatchers.named("hello")).intercept(MethodDelegation.to(Target.class))
                .make()
                .load(AllExample.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .hello(12345);
        System.out.println(helloWorld);
    }

    /**
     * 如果未定义拦截器，异常：
     * None of [] allows for delegation from public void
     */
    public static class MyIntercept{
        /**
         * RuntimeType注解，禁用严格的类型检查，否则报错
         */
        @RuntimeType
        public static void intercept(@AllArguments Object[] args, @This Object current, @SuperCall Callable<?> callable) throws Exception {
            System.out.println("before call..." + args);
            callable.call();
            System.out.println("after call...");
        }
    }
    public static void delegation2() throws Exception {
        ByteBuddyAgent.install();
        Class<? extends Source> clazz = new ByteBuddy()
                .subclass(Source.class)
                .method(ElementMatchers.<MethodDescription>any())
//                .method(ElementMatchers.<MethodDescription>named("print"))
                .intercept(MethodDelegation.to(MyIntercept.class))
                .make()
                .load(AllExample.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
                .getLoaded();
        Source source = clazz.newInstance();
        source.print("qd", "nj");
        source.hello("qbcdefg...");
    }


    public static void main(String[] args) throws Exception {
//        intercept();
//        delegation();
        delegation2();
    }
}
