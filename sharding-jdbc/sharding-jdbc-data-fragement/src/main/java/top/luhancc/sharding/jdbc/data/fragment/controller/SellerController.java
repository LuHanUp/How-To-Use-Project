package top.luhancc.sharding.jdbc.data.fragment.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.luhancc.sharding.jdbc.data.fragment.entity.ProductInfo;
import top.luhancc.sharding.jdbc.data.fragment.service.ProductService;
import top.luhancc.sharding.jdbc.data.fragment.service.StoreInfoService;

import java.util.List;

/**
 * 卖家商品展示
 */
@RestController
@Api(value = "卖家商品展示", tags = "Seller", description = "卖家商品展示")
public class SellerController {

    @Autowired
    private StoreInfoService storeInfoService;

    @Autowired
    private ProductService productService;

    @ApiOperation("查询商品列表")
    @GetMapping(value = "/products")
    public List<ProductInfo> queryProduct() {
        return productService.queryProduct();
    }

    @ApiOperation("添加商品")
    @ApiImplicitParam(name = "productInfo", required = true, dataType = "ProductInfo", paramType = "body", value = "只需要填写以下参数:" +
            "{\n" +
            "  \"descript\": \"商品描述\",\n" +
            "  \"imageUrl\": \"商品图片\",\n" +
            "  \"price\": 3,\n" +
            "  \"productName\": \"黑马可乐饮料\",\n" +
            "  \"regionCode\": \"410100\",\n" +
            "  \"spec\": \"500ml\",\n" +
            "  \"storeInfoId\": 2\n" +
            "}")
    @PostMapping("/products")
    public String createProject(@RequestBody ProductInfo productInfo) {
        productService.createProduct(productInfo);
        return "创建成功!";
    }


}
