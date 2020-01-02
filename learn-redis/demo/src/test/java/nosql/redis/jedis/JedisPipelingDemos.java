package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/12 17:18
 **/
@Slf4j
public class JedisPipelingDemos {

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
    public void testPipeling() {
        long start = System.currentTimeMillis();
        jedis.del("map");
        Pipeline p = jedis.pipelined();
        jedis.del("map");
        for (int i = 0; i < 20000 ; i++) {
            p.hset("map", "field"+ i, i+"");
        }
        for (int i = 0; i < 20000 ; i++) {
            p.hget("map", "field"+ i);
        }
        List<Object> objects = p.syncAndReturnAll();
        System.out.println("返回结果数：" + objects.size());
        long end = System.currentTimeMillis();
        System.out.println("耗时ms：" + (end-start));
    }

    @Test
    public void testNoPipeling() {
        long start = System.currentTimeMillis();
        jedis.del("map");
        for (int i = 0; i < 10000 ; i++) {
            jedis.hset("map", "field"+ i, i+"");
        }
        for (int i = 0; i < 10000 ; i++) {
            jedis.hget("map", "field"+ i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时ms：" + (end-start));
    }



}
