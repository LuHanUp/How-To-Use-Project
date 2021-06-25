package top.luhan.use.elasticsearch.spring.boot.service;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.luhan.use.elasticsearch.spring.boot.model.JDSkuItem;
import top.luhan.use.elasticsearch.spring.boot.util.ReptileUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            if (!jdSkuItemList.isEmpty()) {
                BulkRequest bulkRequest = new BulkRequest();
                for (JDSkuItem jdSkuItem : jdSkuItemList) {
                    bulkRequest.add(new IndexRequest("jd_goods").id(jdSkuItem.getSkuId()).source(JSON.toJSONString(jdSkuItem), XContentType.JSON));
                }
                restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            }
            return jdSkuItemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String, Object>> search(String keyword, int pageNo, int pageSize) {
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页
        searchSourceBuilder.from((pageNo - 1) * pageSize);
        searchSourceBuilder.size(pageSize);

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<font style='color:red'>").postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 按照商品名称分词查询
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isEmpty(keyword)) {
            queryBuilder.must(QueryBuilders.matchAllQuery());
        } else {
            queryBuilder.must(QueryBuilders.matchQuery("name", keyword));
        }
        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> datas = new ArrayList<>();
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                // 处理名称的高亮
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField nameHighLightField = highlightFields.get("name");
                if (nameHighLightField != null) {
                    String newName = nameHighLightField.getFragments()[0].toString();
                    sourceAsMap.put("name", newName);
                }
                sourceAsMap.put("price", (Long.parseLong(sourceAsMap.get("price").toString()) / 100));
                datas.add(sourceAsMap);
            }
            return datas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
