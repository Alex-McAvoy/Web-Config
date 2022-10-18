package com.controller;

import com.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 登录控制器
 * @Author: Alex McAvoy
 * @Date: 2022/9/22 17:04
 * @Version: 1.0
 **/
@Controller
public class LoginController {

    /**
     * @Description: 登录
     * @Param: [username, password, map, session]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/22 17:14
     **/
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Map<String, Object> map,
                        HttpSession session) {

        if (username.equals("aaa")) { // 用户不存在抛出自定义用户不存异常
            throw new UserNotExistException();
        }

        if (!username.isEmpty() && password.equals("123456")) {
            //将用户名放入session已用于登录拦截检查
            session.setAttribute("loginUser", username);
            //登录成功后，为防止表单重复提交，通过mvc控制器进行重定向
            return "redirect:/main.html";
        } else {
            map.put("msg", "用户名密码错误");
            return "login"; //拼接：classpath:templates/ + ... + .html
        }
    }
}
