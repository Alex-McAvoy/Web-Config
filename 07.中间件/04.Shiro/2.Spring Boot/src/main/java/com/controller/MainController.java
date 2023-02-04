package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 控制器
 * @author Alex McAvoy
 * @date 2022/10/28 22:44
 * @version 1.0
 **/
@Controller
@RequestMapping("/mainController")
public class MainController {
    /**
     * 登录验证
     * @param name 用户名
	 * @param password 密码
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/29 12:10
     **/
    @GetMapping("/userLogin")
    public String userLogin(String name, String password,
                            @RequestParam(defaultValue = "false") Boolean rememberMe,
                            HttpSession httpSession) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(name, password, rememberMe);

        try {
            subject.login(token);
            System.out.println(123);
            httpSession.setAttribute("user", token.getPrincipal().toString());
            return "main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }

    /**
     * 跳转登录页
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/29 12:16
     **/
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录验证 RememberMe
     * @param httpSession HttpSession
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/29 12:28
     **/
    @GetMapping("/userLoginRm")
    public String userLogin(HttpSession httpSession) {
        httpSession.setAttribute("user","rememberMe");
        return "main";
    }

    /**
     * 角色验证
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/29 12:37
     **/
    @RequiresRoles("admin")
    @GetMapping("userLoginRoles")
    @ResponseBody
    public String userLoginRoles() {
        System.out.println("登录认证验证角色");
        return "验证角色成功";
    }

    /**
     * 登录认证验证权限
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/10/29 14:25
     **/
    @RequiresPermissions("user:delete")
    @GetMapping("userPermissions")
    @ResponseBody
    public String userLoginPermissions() {
        System.out.println("登录认证验证权限");
        return "验证权限成功";
    }
}
