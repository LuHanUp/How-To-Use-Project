package top.luhancc.activiti.spring.boot.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.luhancc.activiti.spring.boot.config.SecurityUtil;

/**
 * SpringBoot与Activiti7整合的测试
 *
 * @author luHan
 * @create 2021/5/10 16:51
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Activiti7SpringBootTest {
    @Autowired
    private ProcessRuntime processRuntime; // 流程定义相关的操作类
    @Autowired
    private TaskRuntime taskRuntime; // activiti任务相关的操作类
    @Autowired
    private SecurityUtil securityUtil; // SpringSecurity相关的一个工具类

    @Test
    public void testDefinition() {
        securityUtil.logInAs("salaboy");// 使用activiti相关的类时需要先进行认证登录
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {
            log.info("processDefinition:{}", processDefinition);
        }
    }
}
