package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.client.RestTemplate;

/** SpringBoot 使用 SpringData ElasticSearch 模块
 *   - 需要编写继承 AbstractElasticsearchConfiguration 的 ElasticsearchConfig 配置类来设置 ip 地址与端口号
 *   - 通过 ElasticsearchRestTemplate 操作 Elasticsearch
 * **/
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
