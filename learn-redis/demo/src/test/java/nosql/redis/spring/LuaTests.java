package nosql.redis.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/18 13:52
 **/
@Slf4j
public class LuaTests {

    private RedisConnectionFactory connectionFactory;

    private RedisTemplate redisTemplate;

    @Before
    public void init(){
        this.connectionFactory = RedisTemplateFactory.getCluster();
        redisTemplate = new StringRedisTemplate(connectionFactory);
    }

    @Test
    public void execute(){
        String text = "redis.call('SET', KEYS[1], ARGV[1]); return redis.call('GET', KEYS[1])";
        final DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(text);
        redisScript.setResultType(String.class);
        List keys = new ArrayList();
        keys.add("denglj");
        Object r = redisTemplate.execute(redisScript, keys, "40");
        log.info("返回结果：{}", r.toString());

    }

}
