package denglj.learn.spi.db;

import denglj.learn.spi.store.IStore;

public class DBStore implements IStore {

    public String save(String content) {
        return "在数据库保存了：" + content;
    }
}
