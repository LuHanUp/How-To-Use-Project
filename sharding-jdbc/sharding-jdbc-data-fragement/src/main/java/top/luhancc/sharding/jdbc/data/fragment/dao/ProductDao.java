package top.luhancc.sharding.jdbc.data.fragment.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.luhancc.sharding.jdbc.data.fragment.entity.ProductDescript;
import top.luhancc.sharding.jdbc.data.fragment.entity.ProductInfo;

import java.util.List;

@Mapper
@Component
public interface ProductDao {

    @Insert("insert into product_info(store_info_id,product_name,spec,region_code,price,image_url) value(#{storeInfoId},#{productName},#{spec},#{regionCode},#{price},#{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "product_info_id")
    int insertProductInfo(ProductInfo productInfo);

    @Insert("insert into product_descript(product_info_id,descript,store_info_id) value(#{productInfoId},#{descript},#{storeInfoId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertProductDescript(ProductDescript productDescript);

    @Select("select i.*, d.descript from product_info i inner join product_descript d on i.product_info_id = d.product_info_id ")
    List<ProductInfo> selectProductList();

}
