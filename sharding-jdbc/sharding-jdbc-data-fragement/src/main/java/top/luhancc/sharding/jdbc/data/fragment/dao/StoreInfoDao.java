package top.luhancc.sharding.jdbc.data.fragment.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.luhancc.sharding.jdbc.data.fragment.entity.StoreInfo;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StoreInfoDao {

    @Select({"<script>",
            " select",
            " * ",
            " from store_info ",
            " where id in ",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    @MapKey("id")
    Map<Integer, StoreInfo> selectStoreInfoByIds(@Param("ids") List<Long> ids);


}
