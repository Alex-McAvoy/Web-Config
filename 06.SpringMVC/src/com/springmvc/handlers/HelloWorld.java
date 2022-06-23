package com.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/6 10:33
 * @Version: 1.0
 **/

@Controller
public class HelloWorld {
    /**
     * @Description: 通过 @RequestMapping 来映射请求的 URL
     * @Param: []
     * @Return: 返回值通过视图解析器来解析成实际的物理视图，对于 InternalResourceViewResolver 视图解析器，会做如下解析：
     *          prefix + returnVal + suffix 得到实际的物理视图，然后进行转发
     * @Author: Alex McAvoy
     * @Date: 2022/3/6 10:39
     **/
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        return "success";
    }
}
