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

import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.ProtectionDomain;
import java.util.concurrent.Callable;
import java.util.jar.JarFile;

public class PrometheusAgent3 {

    public static void premain(String agentArgs, Instrumentation inst) throws IOException {
        System.out.println("启动prometheus agent3...");
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if(className.equals("org/springframework/boot/SpringApplication")){
                    try {

                        File path = PrometheusJarFinder.findPath();
                        File libPath = new File(path.getPath()+File.separator+"lib");
                        for (File f : libPath.listFiles()) {
                            System.out.println("找到jar包：" + f.getPath());
                            URL url = f.toURL();
                            ClassInjector.addURL((URLClassLoader) loader, url);
                        }
                        Class<?> aClass = loader.loadClass("denglj.learn.prometheus.client.MyCustomServer");
                        Method method1 = aClass.getMethod("start");
                        method1.invoke(aClass.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return classfileBuffer;
            }
        });
    }

}
