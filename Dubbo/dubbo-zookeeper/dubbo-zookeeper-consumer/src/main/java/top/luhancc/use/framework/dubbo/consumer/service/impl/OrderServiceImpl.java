package top.luhancc.use.framework.dubbo.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luhancc.use.framework.dubbo.consumer.service.OrderService;
import top.luhancc.use.framework.dubbo.domain.UserAddress;
import top.luhancc.use.framework.dubbo.service.UserService;

import java.util.List;

/**
 * @author luHan
 * @create 2021/4/19 11:38
 * @since 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;

    @Override
    public void initOrder(Integer userId) {
        // 查询用户的收货地址
        List<UserAddress> userAddressList = userService.getUserAddressList(userId);

        System.out.println(userAddressList);
    }
}
