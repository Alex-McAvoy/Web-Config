package com.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 *  访问客户端
 * @author Alex McAvoy
 * @date 2022/10/13 15:30
 * @version 1.0
 **/
public class Client {
    public static void main(String[] args) throws Exception{
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //关闭ES客户端
        esClient.close();
    }
}
