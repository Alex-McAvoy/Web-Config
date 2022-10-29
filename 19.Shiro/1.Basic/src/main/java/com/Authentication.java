package com;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Description: Shiro 登录认证
 * @Author: Alex McAvoy
 * @Date: 2022/10/27 19:40
 * @Version: 1.0
 **/
public class Authentication {

    private static Subject currentUser;

    public static void main(String[] args) {
        //读取 shiro.ini
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);

        //将defaultSecurityManager绑定到setSecurityManager
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //获取当前用户
        currentUser = SecurityUtils.getSubject();
        testLoginIn();
        testSession();
        testRole();
        testPermission();
        testLoginOut();

        System.exit(0);
    }

    /**
     * @Description: 测试登录认证
     * @Param: [currentUser]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/27 19:45
     **/
    private static void testLoginIn() {
        System.out.println("--------测试用户认证--------");
        //测试当前用户是否已被认证，即是否已登录
        if (!currentUser.isAuthenticated()) {
            //将用户名和密码封装
            UsernamePasswordToken token = new UsernamePasswordToken("user2", "123456");
            //设置 Remember me
            token.setRememberMe(true);

            try { //执行认证，即登录
                currentUser.login(token);
            } catch (UnknownAccountException uae) { //若没有指定的账户
                System.out.println("----> 没有用户： " + token.getPrincipal());
                return;
            } catch (IncorrectCredentialsException ice) { //账户存在，但密码不存在
                System.out.println("----> 用户 " + token.getPrincipal() + " 密码错误！");
                return;
            } catch (LockedAccountException lae) { //用户被锁定
                System.out.println("用户 " + token.getPrincipal() + " 被锁定，请联系管理员");
            } catch (AuthenticationException ae) { //所有认证时异常的父类
                ae.printStackTrace();
            }
        }
        System.out.println("----> 用户 [" + currentUser.getPrincipal() + "] 登录成功！");
    }

    /**
     * @Description: 测试 Session
     * @Param: [currentUser]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/27 19:59
     **/
    private static void testSession() {
        System.out.println("--------测试Session--------");
        //获取当前用户 Session
        Session session = currentUser.getSession();
        //设置Session
        session.setAttribute("someKey", "aValue");
        //获取Session
        String value = (String) session.getAttribute("someKey");
        if ("aValue".equals(value)) {
            System.out.println("----> 检索到正确的值： [" + value + "]");
        }
    }

    /**
     * @Description: 测试用户是否具有角色
     * @Param: [currentUser]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/27 19:47
     **/
    private static void testRole() {
        System.out.println("--------测试用户是否具有角色--------");
        if (currentUser.hasRole("role1")) {
            System.out.println("----> 具有 role1 角色");
        } else {
            System.out.println("----> 不具有 role1 角色");
        }
    }

    /**
     * @Description: 测试权限与行为
     * @Param: [currentUser]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/27 19:57
     **/
    private static void testPermission() {
        System.out.println("--------测试用户是否具有 permission1 权限进行 test 行为--------");
        if (currentUser.isPermitted("permission1:test")) {
            System.out.println("----> 具有 test 行为");
        } else {
            System.out.println("不具有 test 行为");
        }

        System.out.println("--------测试用户是否具有 permission2 权限对 Alex 实体进行 test 行为--------");
        if (currentUser.isPermitted("permission2:delete:Alex")) {
            System.out.println("----> 允许对 Alex 进行 delete");
        } else {
            System.out.println("不允许对 Alex 进行 delete");
        }
    }
    
    /**
     * @Description: 测试登出
     * @Param: [currentUser]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/27 19:57
     **/
    private static void testLoginOut() {
        System.out.println("--------测试用户是否登出--------");
        /** 通过判断用户是否已被认证，从而判断用户是否登出 **/
        System.out.println("----> 用户是否已被认证：" + currentUser.isAuthenticated());
        currentUser.logout(); //执行登出
        System.out.println("----> 用户是否已被认证：" + currentUser.isAuthenticated());
    }
}
