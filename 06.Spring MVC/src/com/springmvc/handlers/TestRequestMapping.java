package com.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/6 11:38
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/test1")
public class TestRequestMapping {
    private static final String SUCCESS = "success";

    /**
     * @Description: 映射 URL：类 @RequestMapping + 方法 @RequestMapping
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 16:23
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
     * @Description: method 属性指定请求方式
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 11:44
     **/
    @RequestMapping(value = "testRequestMappingMethod", method = RequestMethod.POST)
    public String testRequestMappingMethod() {
        System.out.println("Test RequestMapping Method");
        return SUCCESS;
    }

    /**
     * @Description: params 属性指定请求参数
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 11:48
     **/
    @RequestMapping(value = "testRequestMappingParam", params = {"username", "age!=10"})
    public String testRequestMappingParam() {
        System.out.println("Test RequestMapping Param");
        return SUCCESS;
    }

    /**
     * @Description: headers 属性指定请求头,若请求头非指定的 headers 则无法访问
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 11:52
     **/
    @RequestMapping(value = "testRequestMappingHeader", headers = {"Accept-Language=zh-CN,zh;q=0.8"})
    public String testRequestMappingHeader() {
        System.out.println("Test RequestMapping Header");
        return SUCCESS;
    }

    /**
     * @Description: @RequestMapping 支持 Ant 方式的通配符，其仅有三种：
     * - ?: 匹配文件名中的一个字符
     * - *: 匹配文件名中的任意字符
     * - **: 匹配多层路径
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 12:01
     **/
    @RequestMapping("testAntPath/*/abc")
    public String testAntPath() {
        System.out.println("Test Ant Path");
        return SUCCESS;
    }

    /**
     * @Description: @PathVariable 可映射 URL 中的占位符到目标方法的参数中
     * @Param: [id]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 16:06
     **/
    @RequestMapping("testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("Test PathVariable: " + id);
        return SUCCESS;
    }

    /**
     * @Description: @RequestParam 用来映射请求参数
     * - value 值为请求参数的参数名
     * - required 为该参数是否必须，默认为 true
     * - defaultValue 为请求参数默认值
     * @Param: [username, age]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 17:01
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
     * @Description: @RequestHeader 来映射请求头，属性同 @RequestParam
     * @Param: [al]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 17:07
     **/
    @RequestMapping("testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
        System.out.println("Test RequestHeader, Accept-Language:" + al);
        return SUCCESS;
    }

    /**
     * @Description: @CookieValue 映射一个 Cookie 值，属性同 @RequestParam
     * @Param: [sessionId]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 20:01
     **/
    @RequestMapping("testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("Test CookieValue, sessionId:" + sessionId);
        return SUCCESS;
    }

    /**
     * @Description: 重定向
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/8 9:54
     **/
    @RequestMapping("testRedirect")
    public String testRedirect() {
        System.out.println("Test Redirect");
        return "redirect:/index.jsp";
    }

    /**
     * @Description: 转发
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/8 9:54
     **/
    @RequestMapping("testForward")
    public String testForward() {
        System.out.println("Test Forward");
        return "forward:/index.jsp";
    }
}
