package denglj.learn.agent.app;

public class CostTimeApp {
    private void fun1() throws InterruptedException {
        System.out.println("this is fun 1.");
        Thread.sleep(500L);
    }

    private void fun2() throws InterruptedException {
        System.out.println("this is fun 2.");
        Thread.sleep(500L);
    }

    public static void main(String[] args) throws InterruptedException {
        CostTimeApp app = new CostTimeApp();
        app.fun1();
        app.fun2();
    }
}
