package nosql.redis.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/15 13:39
 **/
@Slf4j
public class SpringDataRedisTests {

    private RedisTemplate redisTemplate;

    @Before
    public void init(){
        RedisConnectionFactory connectionFactory = RedisTemplateFactory.getCluster();
        redisTemplate = new StringRedisTemplate(connectionFactory);
    }

    @Test
    public void test1(){
        redisTemplate.delete("hello");
        redisTemplate.opsForValue().set("hello", "world");
        log.info(redisTemplate.opsForValue().get("hello").toString());
    }

    /**
     * jedis集群不支持
     * lettuce支持
     */
    @Test
    public void testPipeline(){
        long start = System.currentTimeMillis();
        redisTemplate.delete("map");
        List list = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> ops) throws DataAccessException {
                for (int i = 0; i < 1000; i++) {
                    ops.opsForHash().put((K) "map", "field" + i, i + "");
                }
                for (int i = 0; i < 1000; i++) {
                    ops.opsForHash().get((K) "map", "field" + i);
                }
                return null;
            }
        });
//        list.forEach(o -> log.info(o + ""));
        long end = System.currentTimeMillis();
        log.info("耗时：{}ms", (end-start));
    }

    @Test
    public void testNoPipeline(){
        long start = System.currentTimeMillis();
        redisTemplate.delete("map");
        for (int i = 0; i < 1000; i++) {
            redisTemplate.opsForHash().put("map", "field" + i, i + "");
        }
        for (int i = 0; i < 1000; i++) {
            redisTemplate.opsForHash().get("map", "field" + i);
        }
//        list.forEach(o -> log.info(o + ""));
        long end = System.currentTimeMillis();
        log.info("耗时：{}ms", (end-start));
    }

    /**
     * lettuce不支持
     */
    @Test
    public void testTransaction(){
        redisTemplate.delete("map");
        List list = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> ops) throws DataAccessException {
                ops.multi();
                ops.opsForValue().set((K)"a", (V)"111");
                ops.opsForValue().set((K)"b", (V)"222");
                ops.opsForValue().set((K)"c", (V)"333");
                ops.exec();

                return null;
            }
        });
    }

}
