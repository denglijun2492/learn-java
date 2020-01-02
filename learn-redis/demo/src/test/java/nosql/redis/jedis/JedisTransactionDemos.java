package nosql.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/12/12 17:18
 **/
@Slf4j
public class JedisTransactionDemos {

    private Jedis jedis;

    @Before
    public void init(){
        jedis = createJedis();
    }

    public Jedis createJedis(){
        Jedis jedis = new Jedis("10.11.1.52", 3333);
        return jedis;
    }

    /**
     * 1、单个 Redis 命令的执行是原子性的，但 Redis 没有在事务上增加任何维持原子性的机制，
     * 所以 Redis 事务的执行并不是原子性的。
     *
     * 2、事务可以理解为一个打包的批量执行脚本，但批量指令并非原子化的操作，
     * 中间某条指令的失败不会导致前面已做指令的回滚，也不会造成后续的指令不做。
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        // 监视keys
        jedis.watch("map", "map1");
        //开启事务
        Transaction multi = jedis.multi();
        //在事务体中，当代码出错或监视的key被本事务对象之外的对象修改时，事务不会提交，返回空列表
        multi.hset("map", "aaa", "111");
        multi.hset("map1", "aaa", "222");

        //取消事务，放弃执行事务块内的所有命令
        //multi.discard()

        // 提交事务
        List<Object> result = multi.exec();
        // 判断事务是否成功
        result.isEmpty();
        log.info("执行结果：");
        result.forEach(o -> log.info(o.toString()));
        // 解除监视
        jedis.unwatch();
    }
}
