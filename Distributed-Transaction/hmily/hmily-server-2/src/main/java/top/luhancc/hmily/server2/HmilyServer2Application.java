package top.luhancc.hmily.server2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author luHan
 * @create 2021/6/9 17:29
 * @since 1.0.0
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients("top.luhancc.hmily.server2.feign")
@ComponentScan({"top.luhancc.hmily.server2", "org.dromara.hmily"})
public class HmilyServer2Application {
    public static void main(String[] args) {
        SpringApplication.run(HmilyServer2Application.class, args);
    }
}
