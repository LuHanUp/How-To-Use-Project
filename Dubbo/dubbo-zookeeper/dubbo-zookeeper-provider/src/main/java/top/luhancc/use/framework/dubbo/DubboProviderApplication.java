package top.luhancc.use.framework.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author luHan
 * @create 2021/4/19 13:55
 * @since 1.0.0
 */
public class DubboProviderApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("provider-dubbo.xml");
        applicationContext.start();

        System.in.read();
    }
}
