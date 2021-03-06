package top.luhancc.sharding.jdbc.data.fragment.service;


import top.luhancc.sharding.jdbc.data.fragment.entity.ProductInfo;

import java.util.List;

/**
 * 商品服务
 */
public interface ProductService {


    /**
     * 创建商品
     *
     * @param product 商品信息
     * @return 已创建的商品信息
     */
    void createProduct(ProductInfo product);


    /**
     * 商品列表展示
     *
     * @return 商品列表
     */
    List<ProductInfo> queryProduct();
}
