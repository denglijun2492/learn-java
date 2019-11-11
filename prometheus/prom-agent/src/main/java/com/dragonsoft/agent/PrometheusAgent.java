package com.dragonsoft.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.jar.JarFile;

public class PrometheusAgent {

    public static class TimeInterceptor {
        @RuntimeType
        public static Object intercept(@Origin Method method,
                                       @Origin Class cls,
                                       @AllArguments Object[] allArguments,
                                       @SuperCall Callable<?> callable) throws Exception{
            try {
                if(cls.getSimpleName().equals("ZdrContextLoaderListener")){
                    ClassLoader appLoader = PrometheusAgent.class.getClassLoader();
                    ClassLoader webLoader = Thread.currentThread().getContextClassLoader();
                    MyClassLoader myClassLoader = new MyClassLoader(appLoader, webLoader);

                    String fname = "com.dragonsoft.agent.PrometheusContextListener";
//                    findClassLoaded(fname);
                    Class<?> aClass = myClassLoader.loadClass(fname);
//                    findClassLoaded(fname);
                    System.out.println("------------------------");
                    System.out.println(aClass.getClassLoader());
                    aClass.getMethod("contextInitialized").invoke(allArguments[0]);
                }
                //原函数的执行
                return callable.call();
            }finally {
            }
        }
    }

    private static Instrumentation Inst;

    private static void findClassLoaded(String name){
        for (Class allLoadedClass : Inst.getAllLoadedClasses()) {
            if(allLoadedClass.getName().equals(name)){
                System.out.println("当前已加载类：" + name);
                return;
            }
        }
        System.out.println("当前还没加载类：" + name);
    }

    public static void premain(String agentArgs, Instrumentation inst) throws IOException {
        inst.appendToSystemClassLoaderSearch(new JarFile("Q:\\git\\s1\\learn-java\\prometheus\\prom-agent\\target\\prom-agent.jar"));
        Inst = inst;
        System.out.println("启动prometheus agent...");
        new AgentBuilder
                .Default()
                .type(ElementMatchers.<TypeDescription>any())
                .transform(new AgentBuilder.Transformer(){
                    @Override
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader) {
                        return builder
                                .method(ElementMatchers.<MethodDescription>named("contextInitialized"))
                                .intercept(MethodDelegation.to(TimeInterceptor.class));
                    }
                })
                .installOn(inst);
    }

    public static void main(String[] args) {
        System.out.println(TimeInterceptor.class.getSimpleName());
    }
}
