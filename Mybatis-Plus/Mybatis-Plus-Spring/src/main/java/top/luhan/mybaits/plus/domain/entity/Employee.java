package top.luhan.mybaits.plus.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author luHan
 * @create 2021/4/8 16:11
 * @since 1.0.0
 */
@Data
@TableName(value = "tbl_employee")
public class Employee {

    /**
     * <pre>
     * {@code @TableId}
     * value:设置主键字段名称
     * type：设置主键策略
     *  AUTO:数据库自增，需要数据库支持自增id
     *  INPUT:用户输入id，可以通过配置注册主键生成插件来进行填充主键的值
     *  ASSIGN_UUID：UUID
     *  ASSIGN_ID：使用雪花算法生成主键
     * </pre>
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;
}
