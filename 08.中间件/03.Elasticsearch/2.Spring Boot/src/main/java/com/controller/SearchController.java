package com.controller;

import com.bean.Product;
import com.service.ProductService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  控制器
 * @author Alex McAvoy
 * @date 2022/10/15 13:30
 * @version 1.0
 **/
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    ProductService productService;

    /**
     *  条件搜索
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/15 13:59
     **/
    @GetMapping("/termQuery")
    @ResponseBody
    public String termQuery() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> products = productService.search(termQueryBuilder);
        List<Product> list = new ArrayList<>();
        for (Product product : products) {
            list.add(product);
        }
        return list.toString();
    }

    @GetMapping("/pageQuery/{page}")
    @ResponseBody
    public String pageQuery(@PathVariable("page") Integer page) {
        int currentPage = page; //当前页，第一页从 0 开始
        int pageSize = 5; //每页条数

        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");

        Iterable<Product> products = productService.search(termQueryBuilder, pageRequest);
        List<Product> list = new ArrayList<>();
        for (Product product : products) {
            list.add(product);
        }
        return list.toString();
    }
}
