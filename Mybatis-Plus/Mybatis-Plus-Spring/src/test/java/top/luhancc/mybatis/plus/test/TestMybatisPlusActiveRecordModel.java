package top.luhancc.mybatis.plus.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.luhan.mybaits.plus.domain.entity.EmployeeARModel;

/**
 * @author luHan
 * @create 2021/4/9 14:12
 * @since 1.0.0
 */
@Log4j
public class TestMybatisPlusActiveRecordModel {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

    @Test
    public void testARInsert() {
        EmployeeARModel employeeARModel = new EmployeeARModel();
        employeeARModel.setLastName("ActiveRecord");
        employeeARModel.setEmail("ActiveRecord@qq.com");
        employeeARModel.setGender(1);
        employeeARModel.setAge(1);

        boolean insert = employeeARModel.insert();
        System.out.println("插入成功吗？" + insert);
    }

    @Test
    public void testARUpdate() {
        EmployeeARModel employeeARModel = new EmployeeARModel();
        employeeARModel.setId(6);
        employeeARModel.setLastName("ActiveRecord-update");
        employeeARModel.setEmail("ActiveRecord@qq.com");
        employeeARModel.setGender(1);
        employeeARModel.setAge(1);

        boolean insert = employeeARModel.updateById();
        System.out.println("修改成功吗？" + insert);
    }

    @Test
    public void testARDeleteById() {
        EmployeeARModel employeeARModel = new EmployeeARModel();
        boolean delete = employeeARModel.deleteById(6);
        System.out.println("delete:" + delete);
    }

    @Test
    public void testARSelectById() {
        EmployeeARModel employeeARModel = new EmployeeARModel();
        EmployeeARModel result = employeeARModel.selectById(3);
        System.out.println(result);

        // 第二种方式：需要设置id
        employeeARModel = new EmployeeARModel();
        employeeARModel.setId(4);
        result = employeeARModel.selectById();
        System.out.println(result);
    }

    @Test
    public void testARSelectWrapper() {
        EmployeeARModel employeeARModel = new EmployeeARModel();
        QueryWrapper<EmployeeARModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 5);
        EmployeeARModel result = employeeARModel.selectOne(queryWrapper);
        System.out.println(result);
    }

    @Test
    public void testARPageSelect() {
        EmployeeARModel employeeARModel = new EmployeeARModel();
        QueryWrapper<EmployeeARModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 10);
        Page<EmployeeARModel> employeeARModelPage = employeeARModel.selectPage(new Page<>(1, 2), queryWrapper);
        System.out.println(employeeARModelPage);
    }
}
