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
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
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

    /**
     * 获取jd数据,然后存储进ES中
     *
     * @param keyword
     * @return
     */
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

    /**
     * 对关键词进行搜索
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
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

    /**
     * 实现对关键词的自动补全
     *
     * @param keyword 需要补全的关键词
     * @return
     */
    public List<String> autocomplete(String keyword) {

        /* 相当于下面这条命令语句
         * GET /jd_goods/_search
         * {
         *   "_source": false,
         *   "suggest": {
         *     "name_suggest": {
         *       "prefix":${keyword},
         *       "completion":{
         *         "field":"name",
         *         "skip_duplicates":true,
         *         "size":10
         *       }
         *     }
         *   }
         * }
         *
         *
         * 注意：需要完成自动补全的字段必须是complete类型
         */
        CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder("name_completion")
                .prefix(keyword).skipDuplicates(true).size(10);
        SuggestBuilder suggestBuilder = new SuggestBuilder().addSuggestion("name_suggest", completionSuggestionBuilder);
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.suggest(suggestBuilder);
        searchSourceBuilder.fetchSource(false);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            List<String> result = new ArrayList<>();

            Suggest suggest = searchResponse.getSuggest();
            Suggest.Suggestion<CompletionSuggestion.Entry> suggestion = suggest.getSuggestion("name_suggest");
            List<CompletionSuggestion.Entry> suggestionEntries = suggestion.getEntries();
            for (CompletionSuggestion.Entry suggestionEntry : suggestionEntries) {
                List<CompletionSuggestion.Entry.Option> options = suggestionEntry.getOptions();
                for (CompletionSuggestion.Entry.Option option : options) {
                    String text = option.getText().string();
                    result.add(text);
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
