package denglj.learn.execute;


import java.lang.reflect.Method;

/**
 * Created by denglj on 2019/7/11.
 */
public class ExecuteProxy implements Executor {
    private String version;
    private MyExecutorClassLoader classLoader;
    private Executor executor;

    public ExecuteProxy(String version){
        this.version = version;
        classLoader = new MyExecutorClassLoader(version);
        try {
            Class<?> executorClass = classLoader.loadClass("denglj.learn.execute.MyExecutor");
            executor = (Executor)executorClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute(String name) {
        executor.execute(name);
    }

    public String version() {
        return executor.version();
    }

    public static void main(String[] args) {
        ExecuteProxy proxy1 = new ExecuteProxy("v1");
        proxy1.execute("wuxp");
        ExecuteProxy proxy2 = new ExecuteProxy("v2");
        proxy2.execute("denglj");

    }
}
