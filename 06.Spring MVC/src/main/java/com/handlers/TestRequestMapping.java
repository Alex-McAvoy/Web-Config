package com.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * RequestMapping
 * @author Alex McAvoy
 * @date 2022/3/6 11:38
 * @version 1.0
 **/
@Controller
@RequestMapping("/test1")
public class TestRequestMapping {
    private static final String SUCCESS = "success";

    /**
     * 映射 URL：类 @RequestMapping + 方法 @RequestMapping
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 16:23
     **/
    @RequestMapping("/toTestRequestMapping")
    public String toTestRequestMapping() {
        System.out.println("To Test RequestMapping");
        return "testRequestMapping";
    }

    @RequestMapping("testRequestMapping")
    public String testRequestMapping() {
        System.out.println("Test RequestMapping");
        return SUCCESS;
    }

    /**
     * method 属性指定请求方式
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 11:44
     **/
    @RequestMapping(value = "testRequestMappingMethod", method = RequestMethod.POST)
    public String testRequestMappingMethod() {
        System.out.println("Test RequestMapping Method");
        return SUCCESS;
    }

    /**
     * params 属性指定请求参数
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 11:48
     **/
    @RequestMapping(value = "testRequestMappingParam", params = {"username", "age!=10"})
    public String testRequestMappingParam() {
        System.out.println("Test RequestMapping Param");
        return SUCCESS;
    }

    /**
     * headers 属性指定请求头,若请求头非指定的 headers 则无法访问
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 11:52
     **/
    @RequestMapping(value = "testRequestMappingHeader", headers = {"Accept-Language=zh-CN,zh;q=0.8"})
    public String testRequestMappingHeader() {
        System.out.println("Test RequestMapping Header");
        return SUCCESS;
    }

    /**
     * 注解 @RequestMapping 支持 Ant 方式的通配符，其仅有三种：
     * - ?: 匹配文件名中的一个字符
     * - *: 匹配文件名中的任意字符
     * - **: 匹配多层路径
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 12:01
     **/
    @RequestMapping("testAntPath/*/abc")
    public String testAntPath() {
        System.out.println("Test Ant Path");
        return SUCCESS;
    }

    /**
     * 注解@PathVariable映射 URL 中的占位符到目标方法的参数中 
     * @param id id
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 16:06
     **/
    @RequestMapping("testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("Test PathVariable: " + id);
        return SUCCESS;
    }

    /**
     * 注解@RequestParam映射请求参数 
     * - value 值为请求参数的参数名
     * - required 为该参数是否必须，默认为 true
     * - defaultValue 为请求参数默认值
     * @param username 用户名
	 * @param age 年龄
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 17:01
     **/
    @RequestMapping("testRequestParam")
    public String testRequestParam(@RequestParam(value = "username", required = false, defaultValue = "")
                                           String username,
                                   @RequestParam(value = "age", required = false, defaultValue = "0")
                                           int age) {
        System.out.println("Test RequestParam, username = " + username + ", age = " + age);
        return SUCCESS;
    }

    /**
     * 注解@RequestHeader映射请求头，属性同 @RequestParam
     * @param al headers
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 17:07
     **/
    @RequestMapping("testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
        System.out.println("Test RequestHeader, Accept-Language:" + al);
        return SUCCESS;
    }

    /**
     * 注解 @CookieValue 映射一个 Cookie 值，属性同 @RequestParam
     * @param sessionId sessionId
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/6 20:01
     **/
    @RequestMapping("testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("Test CookieValue, sessionId:" + sessionId);
        return SUCCESS;
    }

    /**
     * 重定向
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/8 9:54
     **/
    @RequestMapping("testRedirect")
    public String testRedirect() {
        System.out.println("Test Redirect");
        return "redirect:/index.jsp";
    }

    /**
     * 转发
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/8 9:54
     **/
    @RequestMapping("testForward")
    public String testForward() {
        System.out.println("Test Forward");
        return "forward:/index.jsp";
    }
}
