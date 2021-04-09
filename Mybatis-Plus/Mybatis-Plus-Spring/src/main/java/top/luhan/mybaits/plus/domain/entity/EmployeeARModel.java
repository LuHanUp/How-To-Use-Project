package top.luhan.mybaits.plus.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 使用AR模式，实体类必须要继承Model，并且必须有实体类对应的Mapper接口（哪怕不使用）继承BaseMapper
 *
 * @author luHan
 * @create 2021/4/8 16:11
 * @since 1.0.0
 */
@Data
@TableName(value = "tbl_employee")
public class EmployeeARModel extends Model<EmployeeARModel> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;
}
