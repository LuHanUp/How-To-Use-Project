package top.luhancc.use.framework.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.luhancc.use.framework.dubbo.consumer.service.OrderService;

/**
 * @author luHan
 * @create 2021/4/19 13:51
 * @since 1.0.0
 */
public class DubboConsumerApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer-dubbo.xml");
        applicationContext.start();

        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.initOrder(2);
    }
}
