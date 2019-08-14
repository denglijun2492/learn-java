package denglj.learn.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * Created by denglj on 2019/8/13.
 */
@Configuration
public class MyDruidConfig {

    @Autowired
    private DruidDataSource druidDataSource;


    public MyDruidConfig(){
        System.out.println("dd");
    }
}
