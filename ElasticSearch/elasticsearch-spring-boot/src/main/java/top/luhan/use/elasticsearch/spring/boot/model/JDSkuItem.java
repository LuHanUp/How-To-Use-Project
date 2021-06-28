package top.luhan.use.elasticsearch.spring.boot.model;

import lombok.*;

/**
 * 京东商品简介类
 *
 * @author luHan
 * @create 2021/6/25 11:09
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JDSkuItem {

    /**
     * skuId
     */
    private String skuId;

    /**
     * spuId
     */
    private String spuId;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品价格 分
     */
    private Long price;

    /**
     * 会员专享价格
     */
    private Long memberPrice;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品名称对应completion类型的属性
     */
    private String name_completion;

}
