package top.luhancc.hmily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author luHan
 * @create 2021/6/9 17:16
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class HmilyDiscoverApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmilyDiscoverApplication.class, args);
    }
}
