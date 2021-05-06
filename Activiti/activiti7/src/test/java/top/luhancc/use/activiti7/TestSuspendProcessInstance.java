package top.luhancc.use.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Before;
import org.junit.Test;

/**
 * 挂起和激活流程实例
 *
 * @author luHan
 * @create 2021/5/6 14:16
 * @since 1.0.0
 */
public class TestSuspendProcessInstance {
    private ProcessEngine processEngine;

    @Before
    public void init() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取ProcessEngine,默认情况下,如果数据库中没有相关的表,则会进行创建
        processEngine = processEngineConfiguration.buildProcessEngine();
    }

    /**
     * 挂起所有的流程实例
     */
    @Test
    public void suspendAllProcessInstance() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 得到流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("tripProcess")
                .singleResult();
        // 通过流程定义对象挂起这个定义对象所关联的所有流程实例
        // 一旦流程实例被挂起,那么就不可以继续执行和重新开始一个流程实例
        /**
         * suspendProcessInstances:是否挂起定义下的所有流程实例
         * suspensionDate:流程实例被挂起的时间,如果为null,则立即挂起
         */
        repositoryService.suspendProcessDefinitionById(processDefinition.getId(), true, null);
    }

    /**
     * 激活所有的流程实例
     */
    @Test
    public void activateAllProcessInstance() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 得到流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("tripProcess")
                .singleResult();
        // 激活这个流程定义下的所有流程实例
        /**
         * activateProcessInstances:是否激活所有的流程实例
         * activationDate:激活时间,如果为null,则立即激活
         */
        repositoryService.activateProcessDefinitionById(processDefinition.getId(), true, null);
    }
}
