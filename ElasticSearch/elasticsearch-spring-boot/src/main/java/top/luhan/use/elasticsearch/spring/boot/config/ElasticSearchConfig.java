package top.luhan.use.elasticsearch.spring.boot.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch配置类
 *
 * @author luHan
 * @create 2021/6/25 09:32
 * @since 1.0.0
 */
@Configuration
public class ElasticSearchConfig {
    @Value("${elasticsearch.urls}")
    private String urls;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        String[] urlArray = this.urls.split(",");
        HttpHost[] httpHosts = new HttpHost[urlArray.length];
        for (int i = 0; i < urlArray.length; i++) {
            String[] url = urlArray[i].split(":");
            httpHosts[i] = new HttpHost(url[0], Integer.parseInt(url[1]));
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
