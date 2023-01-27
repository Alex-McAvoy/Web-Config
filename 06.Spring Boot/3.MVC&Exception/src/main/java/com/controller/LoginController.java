package com.controller;

import com.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录控制器
 * @author Alex McAvoy
 * @date 2022/9/22 17:04
 * @version 1.0
 **/
@Controller
public class LoginController {

    /** 登录
     * @param username 用户名
     * @param password 密码
     * @param map 视图 Map
     * @param session HttpSession
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/9/22 17:14
     **/
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Map<String, Object> map,
                        HttpSession session) {

        //用户不存在抛出自定义用户不存异常
        String noExistUsername = "aaa";
        if (username.equals(noExistUsername)) {
            throw new UserNotExistException();
        }

        String psw = "123";
        if (!username.isEmpty() && password.equals(psw)) {
            //将用户名放入session以用于登录拦截检查
            session.setAttribute("loginUser", username);
            //登录成功后，为防止表单重复提交，通过MVC控制器进行重定向
            return "redirect:/main.html";
        } else {
            //登录失败，重新返回登录页
            map.put("msg", "用户名密码错误");
            //拼接：classpath:templates/ + ... + .html
            return "login";
        }
    }
}
