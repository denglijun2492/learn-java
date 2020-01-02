package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/13 13:39
 **/
@Slf4j
public class JedisSubscribe {
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
    public void sub1() throws Exception {
        jedis.subscribe(new JedisPubSub(){
            @Override
            public void onMessage(String channel, String message) {
                log.info("接收到消息：{}", message);
            }
        },"my-topic");
    }

    @Test
    public void sub2() throws Exception {
        jedis.subscribe(new JedisPubSub(){
            @Override
            public void onMessage(String channel, String message) {
                log.info("接收到消息：{}", message);
            }
        },"my-topic");
    }

    @Test
    public void pub() throws Exception {
        jedis.publish("my-topic", "hello world..");
    }
}
