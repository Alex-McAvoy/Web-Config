package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主控制器
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/11/10 16:41
 **/
@Controller
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/add")
    public String page1() {
        return "employee/add.html";
    }

    @GetMapping("/list")
    public String page2() {
        return "employee/list.html";
    }
}
