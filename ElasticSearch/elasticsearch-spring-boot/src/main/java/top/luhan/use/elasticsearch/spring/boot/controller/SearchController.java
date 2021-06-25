package top.luhan.use.elasticsearch.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.luhan.use.elasticsearch.spring.boot.service.ContentService;

import java.util.List;
import java.util.Map;

/**
 * @author luHan
 * @create 2021/6/25 11:50
 * @since 1.0.0
 */
@RestController
public class SearchController {
    @Autowired
    private ContentService contentService;

    @GetMapping("search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,
                                            @PathVariable(value = "pageNo") int pageNo,
                                            @PathVariable("pageSize") int pageSize) {
        return contentService.search(keyword, pageNo, pageSize);
    }
}
