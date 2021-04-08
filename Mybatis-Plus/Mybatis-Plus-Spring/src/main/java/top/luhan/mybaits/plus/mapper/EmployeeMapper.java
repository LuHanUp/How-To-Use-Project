package top.luhan.mybaits.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luhan.mybaits.plus.domain.entity.Employee;

/**
 * Mapper接口：
 * <p>
 * 基于Mybatis：需要在Mapper接口中定义相关的CRUD方法，并且在对应的映射文件Mapper.xml中添加相应的SQL语句
 * <p>
 * 基于Mybatis-Plus：只需要继承{@link BaseMapper}即可
 *
 * @author luHan
 * @create 2021/4/8 17:06
 * @since 1.0.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
