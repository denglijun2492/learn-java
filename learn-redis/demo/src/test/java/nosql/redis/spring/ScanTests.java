package nosql.redis.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.io.IOException;
import java.util.Map;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/18 13:52
 **/
@Slf4j
public class ScanTests {

    private RedisConnectionFactory connectionFactory;

    private RedisTemplate redisTemplate;

    @Before
    public void init(){
        this.connectionFactory = RedisTemplateFactory.getStandlone();
        redisTemplate = new StringRedisTemplate(connectionFactory);
    }

    @Test
    public void scan() throws IOException {
        redisTemplate.delete("map2");
        BoundHashOperations map = redisTemplate.boundHashOps("map2");
        map.put("denglj", "good");
        map.put("hello", "world");
        map.put("banana", "big");

        Cursor<Map.Entry<String, String>> cursor = map.scan(ScanOptions.scanOptions().match("*ll*").count(10).build());
        while (cursor.hasNext()){
            Map.Entry<String, String> obj = cursor.next();
            log.info("匹配到：{}, {}", obj.getKey(), obj.getValue());
        }
        cursor.close();
    }
}
