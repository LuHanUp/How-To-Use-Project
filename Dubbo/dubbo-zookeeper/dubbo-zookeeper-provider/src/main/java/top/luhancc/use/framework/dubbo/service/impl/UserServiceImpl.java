package top.luhancc.use.framework.dubbo.service.impl;

import top.luhancc.use.framework.dubbo.domain.UserAddress;
import top.luhancc.use.framework.dubbo.service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * 用户服务实现类
 * <p>
 * 1. 将服务提供者注册到注册中心  参考provider-dubbo.xml
 *
 * @author luHan
 * @create 2021/4/19 11:31
 * @since 1.0.0
 */
public class UserServiceImpl implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(Integer userId) {
        return Arrays.asList(
                new UserAddress(userId, "上海市松江区"),
                new UserAddress(userId, "北京市昌平区宏福科技园综合楼3层")
        );
    }
}
