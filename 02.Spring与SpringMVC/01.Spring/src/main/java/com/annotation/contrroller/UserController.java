package com.annotation.contrroller;

import com.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 模拟表现层
 * @author Alex McAvoy
 * @date 2022/2/25 0:44
 * @version 1.0
 **/
@Controller
public class UserController {

    //自动在 IOC 容器中寻找是否存在兼容的 Bean
    @Autowired
    private UserService userService;

    public void execute() {
        System.out.println("UserController");
        userService.add();
    }
}
