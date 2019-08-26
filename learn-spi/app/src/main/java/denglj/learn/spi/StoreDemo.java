package denglj.learn.spi;

import denglj.learn.spi.store.IStore;

import java.util.ServiceLoader;


public class StoreDemo {
    public static void main(String[] args) {
        ServiceLoader<IStore> stores = ServiceLoader.load(IStore.class);
        for (IStore store : stores) {
            System.out.println(store.save("附件１..."));
        }
    }
}
