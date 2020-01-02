package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/12 17:18
 **/
@Slf4j
public class JedisStringDemos {

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
    public void testGet() throws InterruptedException {
        //将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
        String old = jedis.getSet("foo", "123456");
        log.info("getSet,返回：{}", old);

        //同时设置一个或多个 key-value 对
        jedis.mset("a", "1", "b", "2", "c", "3");
        //jedis.msetnx() 仅当所有给定 key 都不存在

        //用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
        jedis.setrange("foo", 3, "单位是");
        Thread.sleep(500L);
        log.info("setrange返回：", jedis.get("foo"));

        //strlen
        Long l = jedis.strlen("foo");
        log.info("strlen：{}", l);

        //获取所有(一个或多个)给定 key 的值。
        jedis.set("item1", "abc");
        jedis.set("item2", "def");
        jedis.mget("item1", "item2").forEach(v -> log.info(v));

    }
    @Test
    public void testIncr(){
        //将 key 中储存的数字值增一
        jedis.set("count", "1");
        for (int i = 0; i <5 ; i++) {
            jedis.incr("count");
        }
        jedis.decr("count"); //减一

        //将 key 所储存的值加上给定的增量值
        jedis.set("total", "1");
        jedis.incrBy("total", 10L);
    }




}
