package top.luhancc.use.activiti7.groupTask;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import top.luhancc.use.activiti7.BaseTest;

import java.util.List;

/**
 * 组任务，一个任务可以设置多个用户为候选人，每个候选人都可以办理该任务，但是办理前需要拾取该任务
 * <p>
 * 候选人设置主要是candidateUsers,多个候选人使用,分开  参考holiday3.bpmn
 *
 * @author luHan
 * @create 2021/5/7 14:48
 * @since 1.0.0
 */
public class TestGroupTask extends BaseTest {

    /**
     * 查询候选人任务
     */
    @Test
    public void queryCandidateTask() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .taskCandidateUser("lisi") // 候选人
                .list();

    }

    /**
     * 候选人拾取任务
     * <p>
     * 拾取完任务后就可以处理任务了
     */
    @Test
    public void claimTask() {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskCandidateUser("lisi")
                .singleResult();
        if (task != null) {
            taskService.claim(task.getId(), "lisi");// lisi这个候选人拾取任务
        }
    }

    /**
     * 归还任务
     */
    @Test
    public void returnTask() {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskCandidateUser("lisi")
                .singleResult();
        if (task != null) {
            taskService.setAssignee(task.getId(), null);// 将任务负责人设置为null即为归还任务
//            taskService.unclaim(task.getId());// 此方式不知道可不可以,没有试验过
        }
    }

    @Test
    public void transferTask() {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskCandidateUser("lisi")
                .singleResult();
        if (task != null) {
            taskService.setAssignee(task.getId(), "wangwu");// 将任务移交给wangwu进行处理
        }
    }
}
