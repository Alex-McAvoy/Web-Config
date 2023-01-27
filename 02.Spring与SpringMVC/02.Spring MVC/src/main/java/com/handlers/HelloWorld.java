package com.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/3/6 10:33
 * @version 1.0
 **/

@Controller
public class HelloWorld {
    /**
     * 通过 @RequestMapping 来映射请求的 URL
     * @return 返回值通过视图解析器来解析成实际的物理视图，对于 InternalResourceViewResolver 视图解析器，会做如下解析：
     *          prefix + returnVal + suffix 得到实际的物理视图，然后进行转发
     * @author Alex McAvoy
     * @date 2022/3/6 10:39
     **/
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        return "success";
    }
}
