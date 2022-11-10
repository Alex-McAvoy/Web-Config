package com.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.http.HttpHeaders;

/**
 * @Description: Elasticsearch 配置类
 * @Author: Alex McAvoy
 * @Date: 2022/10/13 22:15
 * @Version: 1.0
 **/
@Configuration
public class ElasticsearchRestTemplateConfig {
    @Value("${spring.elasticsearch.rest.uris}")
    private String uris;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(uris)
                .build();
        return RestClients.create(configuration).rest();
    }
}
