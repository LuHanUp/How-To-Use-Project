package top.luhancc.use.framework.dubbo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author luHan
 * @create 2021/4/19 11:31
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddress implements Serializable {
    private Integer userId;
    private String address;
}
