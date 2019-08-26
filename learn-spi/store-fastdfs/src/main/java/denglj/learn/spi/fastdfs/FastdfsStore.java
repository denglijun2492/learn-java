package denglj.learn.spi.fastdfs;

import denglj.learn.spi.store.IStore;

public class FastdfsStore implements IStore {
    public String save(String content) {
        return "在fastdfs中保存了：" + content;
    }
}
