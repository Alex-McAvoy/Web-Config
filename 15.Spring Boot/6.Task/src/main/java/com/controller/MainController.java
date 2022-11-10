package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主控制器
 * @author: Alex McAvoy
 * @date 2022/9/22 14:51
 * @version 1.0
 **/
@Controller
public class MainController {
    @GetMapping("main")
    public String main() {
        return "main";
    }
}
