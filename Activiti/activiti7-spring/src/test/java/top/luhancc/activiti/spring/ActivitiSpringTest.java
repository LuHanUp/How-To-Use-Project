package top.luhancc.activiti.spring;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * activiti与spring整合是否成功
 * <p>
 * 目标：
 * 1. activiti库中是否有相关的表
 * 2. spring容器中是否有runtimeService、taskService等activiti的一些service对象
 * </p>
 *
 * @author luHan
 * @create 2021/5/10 14:56
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:activiti-spring.xml")
public class ActivitiSpringTest {
    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void testOutRepositoryService() {
        System.out.println("repositoryService对象:" + repositoryService);
    }
}
