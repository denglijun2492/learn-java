package denglj.demo.aware;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import denglj.DemoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/3 15:17
 **/
@SpringBootApplication(
        exclude = {
                MybatisPlusAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                DruidDataSourceAutoConfigure.class
        })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
