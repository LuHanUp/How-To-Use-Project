package top.luhan.use.elasticsearch.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.luhan.use.elasticsearch.spring.boot.model.JDSkuItem;
import top.luhan.use.elasticsearch.spring.boot.service.ContentService;

import java.util.List;

/**
 * @author luHan
 * @create 2021/6/25 11:50
 * @since 1.0.0
 */
@RestController
public class DataController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public List<JDSkuItem> parse(@PathVariable String keyword) throws Exception {
        return contentService.parse(keyword);
    }
}
