package top.luhancc.use.activiti7.assigneeUEL;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import top.luhancc.use.activiti7.BaseTest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 启动流程实例,动态设置assignee
 *
 * @author luHan
 * @create 2021/5/6 14:58
 * @since 1.0.0
 */
public class TestAssigneeUEL extends BaseTest {

    @Test
    public void deployHolidayProcess() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .name("请假流程")
                .addClasspathResource("bpmn/holiday/holiday.bpmn")
                .addClasspathResource("bpmn/holiday/holiday.png")
                .deploy();
        System.out.println("流程部署id:" + deployment.getId());
        System.out.println("流程部署名称:" + deployment.getName());
    }

    @Test
    public void startProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 此时这个map就是这个流程涉及到的所有的动态取值的表达式中的key
        // 比如：${applicantUser}、${dep_manager}、${manager}
        Map<String, Object> map = new HashMap<>();
        map.put("applicantUser", "李四");
        map.put("dep_manager", "金主管");
        map.put("manager", "张经理");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday_process", map);
    }
}
