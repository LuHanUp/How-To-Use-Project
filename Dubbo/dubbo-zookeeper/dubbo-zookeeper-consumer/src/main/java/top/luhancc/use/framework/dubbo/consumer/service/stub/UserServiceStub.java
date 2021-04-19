package top.luhancc.use.framework.dubbo.consumer.service.stub;

import top.luhancc.use.framework.dubbo.domain.UserAddress;
import top.luhancc.use.framework.dubbo.service.UserService;

import java.util.Collections;
import java.util.List;

/**
 * 远程UserService的消费方存根对象
 * <p>
 * 可以做降级、容错处理
 *
 * @author luHan
 * @create 2021/4/19 15:19
 * @since 1.0.0
 */
public class UserServiceStub implements UserService {
    private final UserService userService;

    /**
     * 这个构造方法必须要有,会由dubbo将服务提供者的UserService传递进来
     *
     * @param userService
     */
    public UserServiceStub(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserAddress> getUserAddressList(Integer userId) {
        if (this.userService == null) {
            return Collections.emptyList();
        }
        return this.userService.getUserAddressList(userId);
    }
}
