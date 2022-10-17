package com.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @Description: 增加索引
 * @Author: Alex McAvoy
 * @Date: 2022/10/13 15:57
 * @Version: 1.0
 **/
public class IndexCreate {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);

        //响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作:" + acknowledged);

        esClient.close();
    }


}
