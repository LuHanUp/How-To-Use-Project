package top.luhancc.use.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Before;

/**
 * @author luHan
 * @create 2021/5/6 14:59
 * @since 1.0.0
 */
public class BaseTest {
    protected ProcessEngine processEngine;

    @Before
    public void init() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取ProcessEngine,默认情况下,如果数据库中没有相关的表,则会进行创建
        processEngine = processEngineConfiguration.buildProcessEngine();
    }
}
