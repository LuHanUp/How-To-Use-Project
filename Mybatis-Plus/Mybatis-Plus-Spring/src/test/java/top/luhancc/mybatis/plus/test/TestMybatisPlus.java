package top.luhancc.mybatis.plus.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.luhan.mybaits.plus.domain.entity.Employee;
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

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setLastName("mybatis-plus");
        employee.setEmail("mybatis-plus@baomidou.com");
        employee.setGender(0);
        employee.setAge(3);

        employeeMapper.insert(employee);
    }

    @Test
    public void testEntityWrapper() {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gender", 1).between("age", 10, 50);
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<>(1, 2), queryWrapper);
        log.info(employeePage);
    }
}
