package top.luhancc.use.activiti7;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 测试部署出差流程
 *
 * @author luHan
 * @create 2021/4/27 14:43
 * @since 1.0.0
 */
public class TestActivitiTrip {
    private ProcessEngine processEngine;

    @Before
    public void init() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取ProcessEngine,默认情况下,如果数据库中没有相关的表,则会进行创建
        processEngine = processEngineConfiguration.buildProcessEngine();
    }

    /**
     * 部署流程
     *
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */
    @Test
    public void testDeployment() throws FileNotFoundException, URISyntaxException {
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 使用RepositoryService部署bpmn个png文件到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程")
                .addClasspathResource("bpmn/business-trip.bpmn")
                .addClasspathResource("bpmn/business-trip.png")
                .deploy();
        System.out.println("流程部署id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());

        /**
         * 操作的表有:
         * insert:
         * ACT_RE_PROCDE      流程定义信息，每次流程的启动都会有一条记录，也就是部署一个流程可能会有多条流程记录
         * ACT_RE_DEPLOYMENT  部署记录表，一次部署会生成一条记录
         * ACT_GE_BYTEARRAY   部署记录的资源文件表，存放部署中需要的资源文件
         */
    }

    /**
     * 开始一个出差流程
     */
    @Test
    public void testStartProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 根据流程定义的id启动流程:id是在bpmn中开始节点定义的id
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("tripProcess");
        System.out.println("流程定义id:" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例id:" + processInstance.getId());
        System.out.println("当前活动id:" + processInstance.getActivityId());
    }

    /**
     * 用户查询自己的流程任务
     */
    @Test
    public void testFindPersonTaskList() {
        TaskService taskService = processEngine.getTaskService();
        // 根据流程的key和任务负责人查询当前用户的任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("tripProcess")
                .taskAssignee("张三") // 任务的负责人
                .list();
        for (Task task : taskList) {
            System.out.println("流程实例id:" + task.getProcessInstanceId());
            System.out.println("当前任务id:" + task.getId()); // 2505
            System.out.println("当前任务负责人" + task.getAssignee());
            System.out.println("当前任务名称:" + task.getName());
        }

        /**
         * 重要的执行SQL:
         * SELECT DISTINCT RES.* FROM ACT_RU_TASK RES
         * INNER JOIN ACT_RE_PROCDEF D ON RES.PROC_DEF_ID_ = D.ID_
         * WHERE RES.ASSIGNEE_ = ? and D.KEY_ = ?
         * ORDER BY RES.ID_ ASC
         * LIMIT ? OFFSET ?
         * 张三(String), tripProcess(String), 2147483647(Integer), 0(Integer)
         */
    }

    /**
     * 完成自己的流程任务
     */
    @Test
    public void finishPersonTask() {
        TaskService taskService = processEngine.getTaskService();
        // 根据任务id完成当前任务
//        taskService.complete("2505");// 张三完成申请出差

        /**
         * 操作表：
         *
         * insert into ACT_HI_ACTINST ( ID_, PROC_DEF_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_ID_, TASK_ID_, CALL_PROC_INST_ID_, ACT_NAME_, ACT_TYPE_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, DELETE_REASON_, TENANT_ID_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
         * 5001(String), tripProcess:1:4(String), 2501(String), 2502(String), _3(String), 5002(String), null, 经理审批(String), userTask(String), 李经理(String), 2021-04-27 15:21:31.085(Timestamp), null, null, null, (String)
         *
         * insert into ACT_HI_IDENTITYLINK (ID_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, PROC_INST_ID_) values (?, ?, ?, ?, ?, ?)
         * 5003(String), participant(String), 李经理(String), null, null, 2501(String)
         *
         * insert into ACT_RU_TASK (ID_, REV_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, PRIORITY_, CREATE_TIME_, OWNER_, ASSIGNEE_, DELEGATION_, EXECUTION_ID_, PROC_INST_ID_, PROC_DEF_ID_, TASK_DEF_KEY_, DUE_DATE_, CATEGORY_, SUSPENSION_STATE_, TENANT_ID_, FORM_KEY_, CLAIM_TIME_) values (?, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
         * 5002(String), 经理审批(String), null, null, 50(Integer), 2021-04-27 15:21:31.085(Timestamp), null, 李经理(String), null, 2502(String), 2501(String), tripProcess:1:4(String), _3(String), null, null, 1(Integer), (String), null, null
         *
         * insert into ACT_RU_IDENTITYLINK (ID_, REV_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, PROC_INST_ID_, PROC_DEF_ID_) values (?, 1, ?, ?, ?, ?, ?, ?)
         * 5003(String), participant(String), 李经理(String), null, null, 2501(String), null
         *
         * update ACT_HI_TASKINST set PROC_DEF_ID_ = ?, EXECUTION_ID_ = ?, NAME_ = ?, PARENT_TASK_ID_ = ?, DESCRIPTION_ = ?, OWNER_ = ?, ASSIGNEE_ = ?, CLAIM_TIME_ = ?, END_TIME_ = ?, DURATION_ = ?, DELETE_REASON_ = ?, TASK_DEF_KEY_ = ?, FORM_KEY_ = ?, PRIORITY_ = ?, DUE_DATE_ = ?, CATEGORY_ = ? where ID_ = ?
         * tripProcess:1:4(String), 2502(String), 创建出差申请(String), null, null, null, 张三(String), null, 2021-04-27 15:21:31.003(Timestamp), 1243931(Long), null, tripPeople(String), null, 50(Integer), null, null, 2505(String)
         *
         * update ACT_RU_EXECUTION set REV_ = ?, BUSINESS_KEY_ = ?, PROC_DEF_ID_ = ?, ACT_ID_ = ?, IS_ACTIVE_ = ?, IS_CONCURRENT_ = ?, IS_SCOPE_ = ?, IS_EVENT_SCOPE_ = ?, IS_MI_ROOT_ = ?, PARENT_ID_ = ?, SUPER_EXEC_ = ?, ROOT_PROC_INST_ID_ = ?, SUSPENSION_STATE_ = ?, NAME_ = ?, IS_COUNT_ENABLED_ = ?, EVT_SUBSCR_COUNT_ = ?, TASK_COUNT_ = ?, JOB_COUNT_ = ?, TIMER_JOB_COUNT_ = ?, SUSP_JOB_COUNT_ = ?, DEADLETTER_JOB_COUNT_ = ?, VAR_COUNT_ = ?, ID_LINK_COUNT_ = ? where ID_ = ? and REV_ = ?
         * 2(Integer), null, tripProcess:1:4(String), _3(String), true(Boolean), false(Boolean), false(Boolean), false(Boolean), false(Boolean), 2501(String), null, 2501(String), 1(Integer), null, false(Boolean), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 0(Integer), 2502(String), 1(Integer)
         *
         * update ACT_HI_ACTINST set EXECUTION_ID_ = ?, ASSIGNEE_ = ?, END_TIME_ = ?, DURATION_ = ?, DELETE_REASON_ = ? where ID_ = ?
         * 2502(String), 张三(String), 2021-04-27 15:21:31.026(Timestamp), 1243970(Long), null, 2504(String)
         *
         * delete from ACT_RU_TASK where ID_ = ? and REV_ = ?
         * 2505(String), 1(Integer)
         */


        /**
         * 完成后续的出差流程
         */
//        taskService.complete("5002");// 经理完成审批
//        taskService.complete("7502");// 总经理完成审批
//        taskService.complete("10002");// 财务完成审批
    }
}
