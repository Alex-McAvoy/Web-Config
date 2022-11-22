package com.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/3/6 16:23
 * @version 1.0
 **/
@Controller
@RequestMapping("/test2")
public class TestHiddenHttpMethodFilter {
    private static final String SUCCESS = "success";

    @RequestMapping("/toHiddenHttpMethodFilter")
    public String toHiddenHttpMethodFilter() {
        System.out.println("TO HiddenHttpMethodFilter");
        return "testHiddenHttpMethodFilter";
    }

    /** REST 风格的 URL
     * 以 CRUD 为例：
     *  - 新增： /order POST
     *  - 修改： /order/1 PUT
     *  - 获取： /order/1 GET
     *  - 删除： /order/1 DELETE
     *
     * **/

    /**
     * REST 的 GET 请求
     * @param id id
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 16:35
     **/
    @RequestMapping(value = "testRest/{id}", method = RequestMethod.GET)
    public String testRest(@PathVariable Integer id) {
        System.out.println("Test Rest GET, id: " + id);
        return SUCCESS;
    }

    /**
     * REST 的 POST 请求
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 16:36
     **/
    @RequestMapping(value = "testRest", method = RequestMethod.POST)
    public String testRest() {
        System.out.println("Test Rest POST");
        return SUCCESS;
    }

    /**
     * REST 的 DELETE 请求
     * @param id id
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 16:38
     **/
    @RequestMapping(value = "testRestDelete/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable Integer id) {
        System.out.println("Test Rest DELETE, id: " + id);
        return SUCCESS;
    }

    /**
     * REST 的 PUT 请求
     * @param id id
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 16:39
     **/
    @RequestMapping(value = "testRestPut/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id) {
        System.out.println("Test Rest PUT, id: " + id);
        return SUCCESS;
    }
}
