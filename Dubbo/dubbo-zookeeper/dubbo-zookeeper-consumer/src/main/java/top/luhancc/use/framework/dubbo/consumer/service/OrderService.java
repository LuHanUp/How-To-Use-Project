package top.luhancc.use.framework.dubbo.consumer.service;

/**
 * @author luHan
 * @create 2021/4/19 11:37
 * @since 1.0.0
 */
public interface OrderService {

    /**
     * 初始化订单
     * <p>
     * 调用用户服务提供者查询用户的收货地址
     *
     * @param userId
     */
    void initOrder(Integer userId);
}
