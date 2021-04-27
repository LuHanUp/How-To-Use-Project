package top.luhancc.use.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 下载流程的资源文件
 *
 * @author luHan
 * @create 2021/4/27 16:36
 * @since 1.0.0
 */
public class TestDownloadResource {
    private ProcessEngine processEngine;

    @Before
    public void init() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取ProcessEngine,默认情况下,如果数据库中没有相关的表,则会进行创建
        processEngine = processEngineConfiguration.buildProcessEngine();
    }

    @Test
    public void download() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        InputStream resourceAsStream = repositoryService.getResourceAsStream("1", "bpmn/business-trip.png");
        // 对流进行下载即可
    }
}
