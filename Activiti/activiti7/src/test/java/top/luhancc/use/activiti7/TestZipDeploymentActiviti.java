package top.luhancc.use.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;

/**
 * 使用ZIP包来部署一个流程
 *
 * @author luHan
 * @create 2021/4/27 15:45
 * @since 1.0.0
 */
public class TestZipDeploymentActiviti {
    private ProcessEngine processEngine;

    @Before
    public void init() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取ProcessEngine,默认情况下,如果数据库中没有相关的表,则会进行创建
        processEngine = processEngineConfiguration.buildProcessEngine();
    }

    @Test
    public void deploymentByZip() throws FileNotFoundException {
        RepositoryService repositoryService = processEngine.getRepositoryService();

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("xxxx.zip"));
        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream) // 添加一个zip文件
                .deploy();
        System.out.println("流程部署id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());
    }
}
