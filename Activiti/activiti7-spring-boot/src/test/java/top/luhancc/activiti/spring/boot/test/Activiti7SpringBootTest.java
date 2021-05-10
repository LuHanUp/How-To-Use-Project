package top.luhancc.activiti.spring.boot.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
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

    /**
     * 获取流程定义信息
     */
    @Test
    public void testDefinition() {
        securityUtil.logInAs("salaboy");// 使用activiti相关的类时需要先进行认证登录
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {
            log.info("processDefinition:{}", processDefinition);
        }
    }

    /**
     * 启动一个流程实例
     */
    @Test
    public void testStartProcess() {
        securityUtil.logInAs("salaboy");// 使用activiti相关的类时需要先进行认证登录
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("myProcess_1") // 通过流程定义的key来启动流程实例
                .build()
        );
        log.info("启动的流程实例:{}", processInstance);
    }

    /**
     * 1. 获取当前登录用户的task
     * 2. 如果有任务,那么先拾取任务
     * 3. 完成任务
     */
    @Test
    public void testUserTask() {
        securityUtil.logInAs("ryandawsonuk");// 使用activiti相关的类时需要先进行认证登录
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
        for (Task task : taskPage.getContent()) {
            log.info("当前用户有的任务:{}", task);
            // 拾取任务
            taskRuntime.claim(TaskPayloadBuilder
                    .claim()
                    .withTaskId(task.getId())
                    .build());
            // 完成任务
            taskRuntime.complete(TaskPayloadBuilder
                    .complete()
                    .withTaskId(task.getId())
                    .build());
        }

        taskPage = taskRuntime.tasks(Pageable.of(0, 10));
        for (Task task : taskPage.getContent()) {
            log.info("当前用户有的任务:{}", task);
        }
    }
}
