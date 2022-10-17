package com;

import com.bean.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/10/14 14:59
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchIndexTest {
    @Autowired(required = false)
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * @Description: 创建索引，在启动时，系统初始化会根据关联的索引名来自动创建
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/14 15:07
     **/
    @Test
    public void createIndex() { 
        System.out.println("创建索引");
    }

    @Test
    public void deleteIndex() {
        String indexName = ".apm-agent-configuration";
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        indexOperations.delete();
        System.out.println("删除索引");
    }
}
