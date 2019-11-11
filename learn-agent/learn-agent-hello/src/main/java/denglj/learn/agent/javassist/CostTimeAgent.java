package denglj.learn.agent.javassist;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class CostTimeAgent {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("启动方法调用耗时统计...");
        ClassFileTransformer transformer = new PerformMonitorTransformer();
        inst.addTransformer(transformer);
    }
}
