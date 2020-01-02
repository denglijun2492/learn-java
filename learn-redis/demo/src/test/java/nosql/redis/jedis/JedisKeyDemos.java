package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/12 17:18
 **/
@Slf4j
public class JedisKeyDemos {

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
    public void testKey() throws InterruptedException {
        jedis.set("foo", "123456");
        log.info("读取到："+ jedis.get("foo"));

        Boolean b = jedis.exists("foo");
        log.info("exists：{}", b);

        Long i = jedis.del("foo");
        log.info("del：{}", i);

        log.info("\nkeys匹配的使用：");
        jedis.set("item1", "redis");
        jedis.set("item2", "mysql");
        jedis.set("item3", "oracle");
        jedis.keys("item*").forEach(k -> log.info("匹配到的key："+ k));
        jedis.del("item1", "item2", "item3");

        //修改key名称
        jedis.set("dragon", "soft");
        //Thread.sleep(1000L);
        jedis.rename("dragon", "dragoninfo");

        //返回存储值的类型
        jedis.hset("mapping", "abc", "123");
        String t = jedis.type("mapping");
        log.info("key存储类型：{}", t);

    }

    /**
     * 失效
     */
    @Test
    public void testExpire(){
        //EX|PX, expire time units: EX = seconds; PX = milliseconds
        //jedis.set("hello", "word", "EX", 5L);

        //失效以秒计
        //jedis.set("hello", "word");
        //jedis.expire("hello", 6);

        //失效 以毫秒计
        //jedis.pexpire()

        //先设置过期时间，在移除过期
        jedis.set("hello", "world", "EX", 5L);
        Long i = jedis.persist("hello");
        log.info("移除过期设置persist，返回值：{}", i);
    }



}
