package com.annotation.contrroller;

import com.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/25 0:44
 * @Version: 1.0
 **/

//模拟表现层
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
