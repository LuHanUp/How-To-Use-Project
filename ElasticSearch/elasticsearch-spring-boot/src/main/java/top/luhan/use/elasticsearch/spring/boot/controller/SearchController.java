package top.luhan.use.elasticsearch.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.luhan.use.elasticsearch.spring.boot.service.ContentService;

import java.util.List;
import java.util.Map;

/**
 * @author luHan
 * @create 2021/6/25 11:50
 * @since 1.0.0
 */
@Controller
public class SearchController {
    @Autowired
    private ContentService contentService;

    @GetMapping("search/{keyword}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword,
                                            @PathVariable(value = "pageNo") int pageNo,
                                            @PathVariable("pageSize") int pageSize) {
        return contentService.search(keyword, pageNo, pageSize);
    }

    @GetMapping("search.html")
    public String searchReturnHtml(@RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                   ModelMap modelMap) {
        modelMap.put("keyword", keyword);
        List<Map<String, Object>> dataList = contentService.search(keyword, pageNo, pageSize);
        modelMap.put("items", dataList);
        return "search";
    }
}
