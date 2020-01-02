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
public class JedisSortedSetDemos {

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
        jedis.del("zset");
        jedis.zadd("zset", 100, "xiamen");
        jedis.zadd("zset", 200, "beijing");
        jedis.zadd("zset", 300, "shanghai");

        //jedis.zrange()
        jedis.zrangeWithScores("zset", 0, -1).forEach(item -> log.info("{}, {}", item.getScore(), item.getElement()));
    }

    @Test
    public void testGet() throws Exception {
        jedis.del("zset");
        jedis.zadd("zset", 100, "xiamen");
        jedis.zadd("zset", 200, "beijing");
        jedis.zadd("zset", 300, "shanghai");
        jedis.zadd("zset", 500, "guangzhou");
        jedis.zadd("zset", 600, "chongqing");

        log.info("length: {}", jedis.zcard("zset"));
        log.info("zscore: {}", jedis.zscore("zset", "guangzhou"));
        //Long count = jedis.zlexcount("zset", "100", "500");
        //log.info("zlexcount : {}", count);
        log.info("zrangeWithScores:");
        jedis.zrangeWithScores("zset", 1, 4).forEach(item -> log.info("{}, {}", item.getScore(), item.getElement()));
        log.info("zrangeByScoreWithScores:");
        jedis.zrangeByScoreWithScores("zset", 200, 500).forEach(item -> log.info("{}, {}", item.getScore(), item.getElement()));

        Long zrank = jedis.zrank("zset", "shanghai");
        log.info("shanghai排名：{}", zrank);
    }

    @Test
    public void testRemove(){
        jedis.del("zset");
        jedis.zadd("zset", 100, "xiamen");
        jedis.zadd("zset", 200, "beijing");
        jedis.zadd("zset", 300, "shanghai");
        jedis.zadd("zset", 500, "guangzhou");
        jedis.zadd("zset", 600, "chongqing");

        log.info("移除前，长度：{}", jedis.zcard("zset"));
        jedis.zremrangeByScore("zset", 300, 600);
        log.info("移除后，长度：{}", jedis.zcard("zset"));

        /*log.info("移除前，长度：{}", jedis.zcard("zset"));
        jedis.zrem("zset", "shanghai", "guangzhou");
        log.info("移除后，长度：{}", jedis.zcard("zset"));*/
    }

}
