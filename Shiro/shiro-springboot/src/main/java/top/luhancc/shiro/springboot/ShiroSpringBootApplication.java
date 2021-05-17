package top.luhancc.shiro.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication(scanBasePackages = "top.luhancc.shiro.springboot")
@EntityScan("cn.itcast.shiro.domain")
public class ShiroSpringBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringBootApplication.class, args);
    }

    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

}