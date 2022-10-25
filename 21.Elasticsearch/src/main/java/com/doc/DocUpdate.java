package com.doc;

import com.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Description: 文档修改
 * @Author: Alex McAvoy
 * @Date: 2022/10/13 16:56
 * @Version: 1.0
 **/
public class DocUpdate {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //修改文档
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");
        request.doc(XContentType.JSON,"sex","男");

        //响应信息
        UpdateResponse response = esClient.update(request,RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        esClient.close();
    }
}
