package top.luhancc.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import top.luhan.use.elasticsearch.spring.boot.model.JDSkuItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author luHan
 * @create 2021/6/25 10:43
 * @since 1.0.0
 */
public class GetJDSearchHtmlTest {

    @Test
    public void getJDHtml() throws IOException {
        Document document = Jsoup.parse(new URL("https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=e1d0bf992af34fd494021b656e5919ff"), 30000);
        System.out.println(document.toString());
    }

    @Test
    public void getJDHtml2() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=e1d0bf992af34fd494021b656e5919ff");
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
        } else {
            System.out.println(goodsUlElements.toString());
            Element goodsUlElement = goodsUlElements.get(0);
            Elements liElements = goodsUlElement.getElementsByTag("li");
            for (Element liElement : liElements) {
                String skuId = liElement.attr("data-sku");
                String spuId = liElement.attr("data-spu");
                // 获取图片
                String img = liElement.getElementsByClass("p-img").get(0).getElementsByTag("img").attr("data-lazy-img");
                // 获取商品名称
                String name = liElement.getElementsByClass("p-name").eq(0).text();
                // 获取商品价格
                String priceStr = liElement.getElementsByClass("p-price").eq(0).text();
                priceStr = priceStr.replace("￥", "");
                long price = (long) (Double.parseDouble(priceStr) * 100);
                JDSkuItem jdSkuItem = new JDSkuItem(skuId, spuId, img, price, price, name);
                System.out.println(jdSkuItem);
            }
        }
    }
}
