package top.luhancc.use.framework.dubbo.service;

import top.luhancc.use.framework.dubbo.domain.UserAddress;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author luHan
 * @create 2021/4/19 11:31
 * @since 1.0.0
 */
public interface UserService {
    /**
     * 获取指定用户的收货地址
     *
     * @param userId
     * @return
     */
    List<UserAddress> getUserAddressList(Integer userId);
}
