package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

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
public class JedisHashDemos {

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
        //设置一个值
        jedis.hset("person", "name", "denglj");
        //设置一个Map，注意不是整个覆盖，而是按key覆盖
        Map m = new HashMap();
        m.put("age", "40");
        m.put("address", "厦门");
        jedis.hmset("person", m);
        log.info(jedis.hget("person", "name"));

        //获取多个值
        List<String> r = jedis.hmget("person", "age", "address");
        log.info(r.toString());
        //获取一个值
        String name = jedis.hget("person", "name");
        log.info(name);
        //获取一个map
        Map<String, String> person = jedis.hgetAll("person");
        log.info(person.toString());

        //只有在字段 field 不存在时，设置哈希表字段的值
        jedis.hset("person", "money", "1000");
        jedis.hsetnx("person", "money", "2000");
        log.info(jedis.hget("person", "money"));
    }

    @Test
    public void testGet() throws Exception {
        Map m = new HashMap();
        m.put("age", "40");
        m.put("address", "厦门");
        jedis.hmset("person", m);

        jedis.hkeys("person").forEach(k -> log.info("获取key:{}", k));

        Long len = jedis.hlen("person");
        log.info("key 数量：{}", len);

        jedis.hvals("person").forEach(v -> log.info("获取value:{}", v));

    }
    @Test
    public void testIncr(){
    }




}
