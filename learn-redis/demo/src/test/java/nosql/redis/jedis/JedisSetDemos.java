package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/12 17:18
 **/
@Slf4j
public class JedisSetDemos {

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
        jedis.del("set");
        jedis.sadd("set", "a");
        jedis.sadd("set", "a");
        jedis.sadd("set", "b");
        jedis.sadd("set", "c");

        jedis.smembers("set").forEach(v -> log.info("item：{}", v));

    }

    @Test
    public void testGet() throws Exception {
        jedis.del("set");
        jedis.sadd("set", "a");
        jedis.sadd("set", "b");
        jedis.sadd("set", "c");
        jedis.sadd("set", "d");

        log.info("数量：{}", jedis.scard("set"));
        log.info("b是否在集合：{}", jedis.sismember("set", "d"));
        jedis.smembers("set").forEach(v -> log.info("item：{}", v));

        log.info("返回集合中一个或多个随机数：");
        jedis.srandmember("set", 2).forEach(v -> log.info(v));
    }

    @Test
    public void testRemove(){
        jedis.del("list");
        jedis.del("set");
        jedis.sadd("set", "a");
        jedis.sadd("set", "b");
        jedis.sadd("set", "c");
        jedis.sadd("set", "d");

        //移除并返回集合中的一个随机元素
        jedis.spop("set");

        jedis.smembers("set").forEach(v -> log.info("item：{}", v));
    }

    @Test
    public void testDiff(){
        jedis.del("set1");
        jedis.sadd("set1", "a");
        jedis.sadd("set1", "b");
        jedis.sadd("set1", "c");
        jedis.sadd("set1", "d");

        jedis.del("set2");
        jedis.sadd("set2", "a");
        jedis.sadd("set2", "b");
        jedis.sadd("set2", "e");
        jedis.sadd("set2", "f");

        //返回给定所有集合的差集
        log.info("set1和set2差集：");
        jedis.sdiff("set1", "set2").forEach(v -> log.info(v));

        //返回给定所有集合的交集
        log.info("set1和set2交集：");
        jedis.sinter("set1", "set2").forEach(v -> log.info(v));

        //返回并集
        log.info("set1和set2并集：");
        jedis.sunion("set1", "set2").forEach(v -> log.info(v));
    }



}
