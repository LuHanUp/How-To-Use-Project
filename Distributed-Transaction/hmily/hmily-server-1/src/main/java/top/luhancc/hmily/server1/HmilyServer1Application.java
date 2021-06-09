package top.luhancc.hmily.server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author luHan
 * @create 2021/6/9 17:21
 * @since 1.0.0
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.luhancc.hmily.server1.feign"})
@ComponentScan({"top.luhancc.hmily.server1", "org.dromara.hmily"})
public class HmilyServer1Application {
    public static void main(String[] args) {
        SpringApplication.run(HmilyServer1Application.class, args);
    }
}
