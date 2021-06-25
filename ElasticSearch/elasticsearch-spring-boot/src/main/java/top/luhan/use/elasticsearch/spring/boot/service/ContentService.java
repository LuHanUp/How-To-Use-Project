package top.luhan.use.elasticsearch.spring.boot.service;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;
import top.luhan.use.elasticsearch.spring.boot.model.JDSkuItem;
import top.luhan.use.elasticsearch.spring.boot.util.ReptileUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luHan
 * @create 2021/6/25 11:52
 * @since 1.0.0
 */
@Service
public class ContentService {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    public List<JDSkuItem> parse(String keyword) {
        try {
            List<JDSkuItem> jdSkuItemList = ReptileUtils.reptileJDSkuList(keyword);
            BulkRequest bulkRequest = new BulkRequest();
            for (JDSkuItem jdSkuItem : jdSkuItemList) {
                bulkRequest.add(new IndexRequest("jd_goods").id(jdSkuItem.getSkuId()).source(JSON.toJSONString(jdSkuItem), XContentType.JSON));
            }
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return jdSkuItemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
