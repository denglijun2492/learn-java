package nosql.redis.spring;

import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Arrays;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/18 13:41
 **/
public class RedisTemplateFactory {
    public static RedisConnectionFactory getStandlone(){
        //1.创建单机连接配置
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration("10.11.1.52", 3333);
        //standaloneConfig.setPassword(RedisPassword.of(parameter.getPassword()));

        //2.创建客户端连接池配置
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(50);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        builder.usePooling().poolConfig(poolConfig);
        // 2.1增加连接超时参数配置
        builder.connectTimeout(Duration.ofMillis(2000));
        JedisClientConfiguration jedisClientConfiguration = builder.build();

        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder1 = LettuceClientConfiguration.builder();
        LettuceClientConfiguration build = builder1.build();

        //3.创建redis fctory
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(standaloneConfig);
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfiguration, jedisClientConfiguration);
        connectionFactory.afterPropertiesSet();

        //RedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
        return connectionFactory;
    }

    public static RedisConnectionFactory getCluster(){
        //1.创建单机连接配置
        //集群连接配置
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(
                Arrays.asList(
                        "10.11.0.114:7000",
                        "10.11.0.114:7001",
                        "10.11.0.114:7002",
                        "10.11.0.114:7003",
                        "10.11.0.114:7004",
                        "10.11.0.114:7005"));

        //2.创建客户端连接池配置
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(50);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        builder.usePooling().poolConfig(poolConfig);
        // 2.1增加连接超时参数配置
        builder.connectTimeout(Duration.ofMillis(2000));

        JedisClientConfiguration jedisClientConfiguration = builder.build();


        //3.创建redis fctory
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(clusterConfiguration);
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfiguration, jedisClientConfiguration);
        connectionFactory.afterPropertiesSet();

        //RedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);

        return connectionFactory;
    }
}
