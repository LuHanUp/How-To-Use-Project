package top.luhancc.springmvc.controller.param;

import lombok.Data;

/**
 * 参数类型为Pojo包装类型，也就是Pojo里面含有另一个Pojo
 *
 * @author luHan
 * @create 2021/5/11 15:54
 * @since 1.0.0
 */
@Data
public class QueryVo {
    private Integer id;
    private String name;
    private User user;
}
