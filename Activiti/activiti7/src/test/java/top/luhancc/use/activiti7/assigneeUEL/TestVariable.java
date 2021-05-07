package top.luhancc.use.activiti7.assigneeUEL;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import top.luhancc.use.activiti7.BaseTest;

/**
 * 测试activiti中的变量
 *
 * @author luHan
 * @create 2021/5/6 16:33
 * @since 1.0.0
 */
public class TestVariable extends BaseTest {

    @Test
    public void deployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .name("请假流程")
                .addClasspathResource("bpmn/holiday/holiday2.bpmn")
                .deploy();
        System.out.println("流程部署id:" + deployment.getId());
        System.out.println("流程部署名称:" + deployment.getName());
    }
}
