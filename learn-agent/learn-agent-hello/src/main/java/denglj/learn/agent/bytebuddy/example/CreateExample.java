package denglj.learn.agent.bytebuddy.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/13 13:27
 **/
public class CreateExample {
    /**
     * 默认生成： net.bytebuddy.renamed.java.lang.Object$ByteBuddy$95JUlwvZ
     * 可通过name指定
     * ClassLoadingStrategy类加载策略
     * WRAPPER 创建一个新的Wrapping类加载器
     * CHILD_FIRST 类似上面，但是子加载器优先负责加载目标类
     * INJECTION 利用反射机制注入动态类型
     */
    public static void create1(){
        //make方法返回未加载的Unloaded类，仅创建好字节码
//        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
//                .subclass(Object.class)
//                .make();
        Class<?> loaded = new ByteBuddy()
                .subclass(Object.class)
//                .name("denglj.Foo")
                .make()
                .load(CreateExample.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        System.out.println(loaded);
    }

    public static void main(String[] args) {
        create1();
    }
}
