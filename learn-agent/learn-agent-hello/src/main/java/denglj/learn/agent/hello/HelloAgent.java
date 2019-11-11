package denglj.learn.agent.hello;

import java.lang.instrument.Instrumentation;

public class HelloAgent {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("this is an agent.");
        System.out.println("args:" + agentArgs);
    }
}
