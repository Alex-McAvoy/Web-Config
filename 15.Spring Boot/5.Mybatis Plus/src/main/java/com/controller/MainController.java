package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主控制器
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/9/23 16:25
 **/
@Controller
public class MainController {
    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
