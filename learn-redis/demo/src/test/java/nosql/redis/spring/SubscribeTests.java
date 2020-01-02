package nosql.redis.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/18 13:52
 **/
@Slf4j
public class SubscribeTests {

    private RedisConnectionFactory connectionFactory;

    private RedisTemplate redisTemplate;

    @Before
    public void init(){
        this.connectionFactory = RedisTemplateFactory.getCluster();
        redisTemplate = new StringRedisTemplate(connectionFactory);
    }

    @Test
    public void publish(){
        redisTemplate.convertAndSend("my-topic2", "邓礼俊....");
    }

    @Test
    public void subscribe() throws InterruptedException {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
               log.info("接收到消息：{}", message);
            }
        }, new ChannelTopic("my-topic2"));
        listenerContainer.afterPropertiesSet();
        listenerContainer.start();
        Thread.sleep(100000L);
    }
}
