package denglj.learn.execute;

/**
 * Created by denglj on 2019/7/11.
 */
public class MyExecutor extends AbstractExecutor {

    @Override
    public void execute(final String name) {
        this.handle(new Handler() {
            @Override
            public void handle() {
                System.out.println("v1:" + name);
            }
        });
    }

    public String version() {
        return "v1";
    }
}
