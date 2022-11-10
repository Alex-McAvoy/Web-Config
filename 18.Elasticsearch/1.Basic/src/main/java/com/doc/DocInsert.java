package com.doc;

import com.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 *  文档插入
 * @author Alex McAvoy
 * @date 2022/10/13 16:56
 * @version 1.0
 **/
public class DocInsert {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //转换为JSON形式
        User user = new User("Alex","female",30);
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        //插入文档
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");
        request.source(userJson, XContentType.JSON);

        //响应信息
        IndexResponse response = esClient.index(request,RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        esClient.close();
    }
}
