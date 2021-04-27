package top.luhancc.use.activiti7;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author luHan
 * @create 2021/4/26 18:02
 * @since 1.0.0
 */
public class TestActivitiCreateTable {

    @Test
    public void createTable() throws FileNotFoundException, URISyntaxException {
        URL resource = TestActivitiCreateTable.class.getClassLoader().getResource("activiti.cfg.xml");
        URI uri = resource.toURI();
        InputStream inputStream = new FileInputStream(new File(uri));
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream);
        // 获取ProcessEngine,默认情况下,如果数据库中没有相关的表,则会进行创建
        System.out.println(processEngineConfiguration.buildProcessEngine());
    }
}
