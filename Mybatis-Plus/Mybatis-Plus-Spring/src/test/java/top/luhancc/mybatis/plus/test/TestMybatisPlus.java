package top.luhancc.mybatis.plus.test;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.luhan.mybaits.plus.mapper.EmployeeMapper;

import javax.sql.DataSource;

/**
 * @author luHan
 * @create 2021/4/8 16:54
 * @since 1.0.0
 */
@Log4j
public class TestMybatisPlus {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

    private EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);

    @Test
    public void testDataSource() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        log.info("获取的数据源Bean:" + dataSource);
    }
}
