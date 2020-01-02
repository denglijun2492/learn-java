package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/12 17:18
 **/
@Slf4j
public class JedisClusterDemos {

    private JedisCluster jedisCluster;

    @Before
    public void init(){
        jedisCluster = createJedisCluster();
    }

    public JedisCluster createJedisCluster(){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("10.11.0.114", 7000));
        jedisClusterNodes.add(new HostAndPort("10.11.0.114", 7001));
        jedisClusterNodes.add(new HostAndPort("10.11.0.114", 7002));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        return jc;
    }

    @Test
    public void first(){
        jedisCluster.set("foo", "123456");
        jedisCluster.set("hello", "word");
        log.info("读取到："+ jedisCluster.get("hello"));

        jedisCluster.del("map");
        HashMap map = new HashMap();
        map.put("a", 1+"");
        map.put("b", 1+"");
        map.put("c", 1+"");
        jedisCluster.hmset("map", map);
    }

    @Test
    public void testPipeling() {
//        long start = System.currentTimeMillis();
//        Pipeline p = jedis.pipelined();
//        jedis.del("map");
//        for (int i = 0; i < 10000 ; i++) {
//            p.hset("map", "field"+ i, i+"");
//        }
//        for (int i = 0; i < 10000 ; i++) {
//            p.hget("map", "field"+ i);
//        }
//        List<Object> objects = p.syncAndReturnAll();
//        System.out.println("返回结果数：" + objects.size());
//        long end = System.currentTimeMillis();
//        System.out.println("耗时ms：" + (end-start));
    }

}
