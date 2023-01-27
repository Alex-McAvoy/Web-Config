package com.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 *  文档删除
 * @author Alex McAvoy
 * @date 2022/10/13 16:56
 * @version 1.0
 **/
public class DocDelete {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //删除文档
        DeleteRequest request = new DeleteRequest();
        request.index("user").id("1001");

        //响应信息
        DeleteResponse response = esClient.delete(request,RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        esClient.close();
    }
}
