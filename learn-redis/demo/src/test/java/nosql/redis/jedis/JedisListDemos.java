package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/12 17:18
 **/
@Slf4j
public class JedisListDemos {

    private Jedis jedis;

    @Before
    public void init(){
        jedis = createJedis();
    }

    public Jedis createJedis(){
        Jedis jedis = new Jedis("10.11.1.52", 3333);
        return jedis;
    }

    @Test
    public void testSet() throws Exception {
        jedis.del("list");
        //从头部插入
        jedis.lpush("list", "a");
        jedis.lpush("list", "b");
        jedis.lpush("list", "c");
        //从尾部添加
        jedis.rpush("list", "end");
        //通过索引设置
        jedis.lset("list", 0, "cc");
        //从指定元素的前或后插入
        jedis.linsert("list", ListPosition.BEFORE, "a", "before a");

        Long len = jedis.llen("list");
        log.info("列表长度：{}", len);
        //获取指定范围，stop超过长度不会报错，-1代表全部
        jedis.lrange("list", 0, -1).forEach(v -> log.info("item:{}", v));
    }

    @Test
    public void testGet() throws Exception {
        jedis.del("list");
        jedis.rpush("list", "a");
        jedis.rpush("list", "b");
        jedis.rpush("list", "c");
        jedis.rpush("list", "d");

        log.info("通过索引获取 Lindex：{}", jedis.lindex("list", 2));
        log.info("移出并获取列表的第一个元素 Lindex：{}", jedis.lpop("list"));

        jedis.lrange("list", 0, -1).forEach(v -> log.info("item:{}", v));
    }

    @Test
    public void testRemove(){
        jedis.del("list");
        jedis.rpush("list", "a");
        jedis.rpush("list", "b");
        jedis.rpush("list", "c");
        jedis.rpush("list", "d");

        //对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
        jedis.ltrim("list", 1, 2);
        jedis.lrange("list", 0, -1).forEach(v -> log.info("item:{}", v));
    }

    /**
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     */
    @Test
    public void testLrem(){
        jedis.del("list");
        jedis.rpush("list", "a");
        jedis.rpush("list", "b");
        jedis.rpush("list", "c");
        jedis.rpush("list", "b");

        jedis.lrem("list", -2, "b");
        jedis.lrange("list", 0, -1).forEach(v -> log.info("item:{}", v));
    }




}
