package top.luhan.use.elasticsearch.spring.boot.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import top.luhan.use.elasticsearch.spring.boot.model.JDSkuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 爬虫工具类
 *
 * @author luHan
 * @create 2021/6/25 11:21
 * @since 1.0.0
 */
public class ReptileUtils {

    public static void main(String[] args) throws Exception {
        ReptileUtils.reptileJDSkuList("java").forEach(System.out::println);
    }

    public static List<JDSkuItem> reptileJDSkuList(String keyword) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://search.jd.com/Search?keyword=" + keyword + "&enc=utf-8&wq=" + keyword + "&pvid=e1d0bf992af34fd494021b656e5919ff");
        httpGet.addHeader("Cookie", "ipLoc-djd=1-72-2799-0");
        httpGet.addHeader("Cache-Control", "no-cache");
        httpGet.addHeader("User-Agent", "PostmanRuntime/7.26.8");
        httpGet.addHeader("Accept", "*/*");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.addHeader("Connection", "keep-alive");
        HttpResponse httpResponse = httpClient.execute(httpGet);
        String htmlStr = EntityUtils.toString(httpResponse.getEntity());
        Document document = Jsoup.parse(htmlStr);
        Elements goodsUlElements = document.getElementsByClass("gl-warp clearfix");
        if (goodsUlElements.isEmpty()) {
            System.out.println("没有获取到goodsUlElement元素");
            return Collections.emptyList();
        } else {
            System.out.println(goodsUlElements.toString());
            List<JDSkuItem> jdSkuItemList = new ArrayList<>();
            Element goodsUlElement = goodsUlElements.get(0);
            for (Node childNode : goodsUlElement.childNodes()) {
                if (childNode instanceof Element) {
                    Element liElement = (Element) childNode;
                    String skuId = liElement.attr("data-sku");
                    String spuId = liElement.attr("data-spu");
                    // 获取图片
                    String img = liElement.getElementsByClass("p-img").get(0).getElementsByTag("img").attr("data-lazy-img");
                    // 获取商品名称
                    String name = liElement.getElementsByClass("p-name").eq(0).text();
                    // 获取商品价格
                    String priceStr = liElement.getElementsByClass("p-price").eq(0).text();
                    priceStr = priceStr.replace("￥", "");
                    String memberPriceStr = priceStr;
                    if (priceStr.contains(" ")) {
                        // 说明含有会员专享价格
                        String[] pricesStr = priceStr.split(" ");
                        priceStr = pricesStr[0];
                        memberPriceStr = pricesStr[1];
                        memberPriceStr = memberPriceStr.replace("人拼", "");
                    }
                    long price = (long) (Double.parseDouble(priceStr) * 100);
                    long memberPrice = (long) (Double.parseDouble(memberPriceStr) * 100);
                    JDSkuItem jdSkuItem = new JDSkuItem(skuId, spuId, img, price, memberPrice, name);
                    jdSkuItemList.add(jdSkuItem);
                }
            }
//            Elements liElements = goodsUlElement.getElementsByTag("li");
//            for (Element liElement : liElements) {
//                String skuId = liElement.attr("data-sku");
//                String spuId = liElement.attr("data-spu");
//                // 获取图片
//                String img = liElement.getElementsByClass("p-img").get(0).getElementsByTag("img").attr("data-lazy-img");
//                // 获取商品名称
//                String name = liElement.getElementsByClass("p-name").eq(0).text();
//                // 获取商品价格
//                String priceStr = liElement.getElementsByClass("p-price").eq(0).text();
//                priceStr = priceStr.replace("￥", "");
//                long price = (long) (Double.parseDouble(priceStr) * 100);
//                JDSkuItem jdSkuItem = new JDSkuItem(skuId, spuId, img, price, name);
//                jdSkuItemList.add(jdSkuItem);
//
//            }
            return jdSkuItemList;
        }
    }
}
