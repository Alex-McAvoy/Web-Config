package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 索引操作控制器
 * @Author: Alex McAvoy
 * @Date: 2022/10/14 15:22
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * @Description: 创建索引
     * @Param: [indexName]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/10/14 15:26
     **/
    @GetMapping("/create/{indexName}")
    @ResponseBody
    public String create(@PathVariable("indexName") String indexName) {
        System.out.println(indexName);
        System.out.println(elasticsearchRestTemplate);
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        if(indexOperations.exists()){
            return "索引已存在";
        }
        indexOperations.create();
        return "索引创建成功";
    }

    /**
     * @Description: 删除索引
     * @Param: [indexName]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/10/14 15:26
     **/
    @GetMapping("/delete/{indexName}")
    @ResponseBody
    public String delete(@PathVariable("indexName") String indexName) {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        if(!indexOperations.exists()){
            return "索引不存在";
        }
        indexOperations.delete();
        return "索引删除成功";
    }
}
