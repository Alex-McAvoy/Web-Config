package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/11/9 16:39
 **/
@Controller
@RequestMapping("/user")
public class LoginController {

    /*** 不需登录认证即可访问
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/11/3 22:41
     **/
    @GetMapping("/hello")
    @ResponseBody
    public String helloSecurity() {
        return "Hello Security";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 认证访问后的主页
     *
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/11/3 22:41
     **/
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
