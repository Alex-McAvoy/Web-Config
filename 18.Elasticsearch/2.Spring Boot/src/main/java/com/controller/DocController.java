package com.controller;

import com.bean.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  文档操作控制器
 * @author Alex McAvoy
 * @date 2022/10/14 16:33
 * @version 1.0
 **/
@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    ProductService productService;

    /**
     *  保存
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 17:07
     **/
    @GetMapping("/save")
    @ResponseBody
    public String save() {
        Product product1 = new Product(1, "华为P20", "手机", 2999.0, "http://www.test.com/hwp20.jpg");
        Product product2 = new Product(2, "华为P40", "手机", 4999.0, "http://www.test.com/hwp40.jpg");
        Product product3 = new Product(3, "苹果11", "手机", 5999.0, "http://www.test.com/iphone11.jpg");
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        return "保存成功";
    }

    /**
     *  更新
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 17:07
     **/
    @GetMapping("/update")
    @ResponseBody
    public String update() {
        Product product = new Product(1, "华为P20", "手机", 999999.0, "http://www.test.com/hwp20.jpg");
        productService.save(product);
        return "修改成功";
    }

    /**
     *  查找
     * @param id id
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 17:07
     **/
    @GetMapping("/findById/{id}")
    @ResponseBody
    public String findById(@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        return product.toString();
    }

    /**
     *  查找所有
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 17:08
     **/
    @GetMapping("/findAll")
    @ResponseBody
    public String findAll() {
        Iterable<Product> products = productService.findAll();
        List<Product> list = new ArrayList<>();
        for (Product product : products) {
            list.add(product);
        }
        return list.toString();
    }

    /**
     *  根据id删除
     * @param id id
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 17:13
     **/
    @GetMapping("/deleteById/{id}")
    @ResponseBody
    public String deleteById(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "删除成功";
    }

    /**
     *  批量新增
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 17:21
     **/
    @GetMapping("/saveAll")
    @ResponseBody
    public String saveAll() {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(i);
            product.setProductName("小米手机" + i);
            product.setCategory("手机");
            product.setPrice(2000.0 + i);
            product.setImagesUrl("http://www.test.com/xiaomi_" + i + ".jpg");
            list.add(product);
        }
        productService.saveAll(list);
        return "批量添加成功";
    }

    /**
     *  分页查询
     * @param page 分页数
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/14 17:36
     **/
    @GetMapping("/findAllByPage/{page}")
    @ResponseBody
    public String findAll(@PathVariable("page") Integer page) {
        int currentPage = page; //当前页，第一页从 0 开始
        int pageSize = 5; //每页条数
        Sort sort = Sort.by(Sort.Direction.DESC, "id"); //排序方式
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize, sort);

        Page<Product> productPage = productService.findAll(pageRequest);
        List<Product> list = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            list.add(product);
        }

        return list.toString();
    }

    /**
     *  排序查询
     * @param sortWay 排序方式
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/15 13:30
     **/
    @GetMapping("/findAllBySort/{sortWay}")
    @ResponseBody
    public String findAll(@PathVariable("sortWay") String sortWay) {
        Sort sort = null;
        if (sortWay.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, "id"); //排序方式
        }
        else if (sortWay.equals("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, "id"); //排序方式
        } else {
            return "请指定排序方式";
        }

        Iterable<Product> productSort = productService.findAll(sort);
        List<Product> list = new ArrayList<>();
        for (Product product : productSort) {
            list.add(product);
        }

        return list.toString();
    }
}
