package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *  索引操作控制器
 * @author Alex McAvoy
 * @date 2022/10/14 15:22
 * @version 1.0
 **/
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     *  创建索引
     * @param indexName 索引名
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 15:26
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
     *  删除索引
     * @param indexName 索引名
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 15:26
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
