package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @RestController: @Controller 与 @ResponseBody 的合体
 *  @Controller: 标识控制类
 *  @ResponseBody: 标识该类所有方法返回数据写给浏览器，若为对象转为Json
 **/
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
