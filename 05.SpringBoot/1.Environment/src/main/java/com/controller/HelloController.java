package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 控制器
 * - @Controller: 标识控制类
 * - @RestController: @Controller 与 @ResponseBody 的合体
 * - @ResponseBody: 标识该类所有方法返回数据写给浏览器，若为对象转为Json
 *
 * @author Alex McAvoy
 * @date 2022/10/10 8:59
 * @version 1.0
 **/
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
