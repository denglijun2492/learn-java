package denglj.learn.execute;

/**
 * Created by denglj on 2019/7/10.
 */
public abstract class AbstractExecutor implements Executor {

    @Override
    public void execute(final String name) {
        this.handle(new Handler() {
            @Override
            public void handle() {
                System.out.println("version: " + name);
            }
        });
    }

    protected void handle(Handler handler){
        handler.call();
    }

    protected abstract class Handler{
        public void call(){
            ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
            //临时更改ClassLoader
            Thread.currentThread().setContextClassLoader(AbstractExecutor.class.getClassLoader());

            handle();

            //还原之前的ClassLoader
            Thread.currentThread().setContextClassLoader(oldClassLoader);
        }

        public abstract void handle();
    }
}
