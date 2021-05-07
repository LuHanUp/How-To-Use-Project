package top.luhancc.use.activiti7.variable;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import top.luhancc.use.activiti7.BaseTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 给activiti中设置global variable的方式
 * <p>
 * 1. 启动一个流程实例时设置
 * 2. 完成任务时设置
 * 注意：必须在需要使用到变量之前的节点任务进行设置,否则会影响整个流程的运作
 * 3. 在流程实例中设置
 * </p>
 *
 * @author luHan
 * @create 2021/5/7 11:45
 * @since 1.0.0
 */
public class TestSetGlobalVariable extends BaseTest {

    /**
     * 在开始一个流程实例时设置variable
     */
    @Test
    public void setByStartProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<>();
        // 如果变量是一个pojo类型,那么需要实现序列化接口,否则会抛出异常
        variables.put("days", 3);// 设置请假天数
        runtimeService.startProcessInstanceByKey("holiday_process2", variables);
    }

    /**
     * 在完成一个任务的时候设置variable
     */
    @Test
    public void setByCompleteTask() {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskAssignee("zhangsan") // 任务完成人
                .processDefinitionKey("holiday_process2")
                .singleResult();
        Map<String, Object> variables = new HashMap<>();
        // 如果变量是一个pojo类型,那么需要实现序列化接口,否则会抛出异常
        variables.put("days", 3);// 设置请假天数
        taskService.complete(task.getId(), variables);
    }

    /**
     * 通过流程实例设置variable
     */
    @Test
    public void setByProcessInstance() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        /**
         * executionId:流程实例的id
         * variableName:变量名
         * value:变量值
         */
//        runtimeService.setVariable("2501", "days", 3);
        Map<String, Object> variables = new HashMap<>();
        // 如果变量是一个pojo类型,那么需要实现序列化接口,否则会抛出异常
        variables.put("days", 3);// 设置请假天数
        runtimeService.setVariables("2501", variables);
    }

    @Test
    public void setByTask() {
        TaskService taskService = processEngine.getTaskService();

        /**
         * String taskId:任务id
         * String variableName:变量名称
         * Object value:变量值
         */
//        taskService.setVariable("1", "days", 3);

        Map<String, Object> variables = new HashMap<>();
        // 如果变量是一个pojo类型,那么需要实现序列化接口,否则会抛出异常
        variables.put("days", 3);// 设置请假天数
        taskService.setVariables("1", variables);
    }

}
