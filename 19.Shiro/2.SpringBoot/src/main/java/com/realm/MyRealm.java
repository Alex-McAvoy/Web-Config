package com.realm;

import com.bean.User;
import com.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 自定义 Realm
 * @Author: Alex McAvoy
 * @Date: 2022/10/28 22:27
 * @Version: 1.0
 **/

/**
 * @description:
 * @param:
 * @return:
 * @author: Alex McAvoy
 * @date: 2022/10/28 22:55
 **/
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * @Description: 自定义授权方法，获取当前登录用户权限信息，返回给 Shiro 用来进行授权对比
     * @Param: [principalCollection]
     * @Return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: Alex McAvoy
     * @Date: 2022/10/28 22:28
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入自定义授权方法");

        //获取当前用户身份信息
        String principal = principalCollection.getPrimaryPrincipal().toString();

        //调用接口方法获取用户的角色信息
        List<String> roles = userService.getUserRoleInfo(principal);
        System.out.println("当前用户角色信息: " + roles);

        //调用接口方法获取用户角色的权限信息
        List<String> permissions = userService.getUserPermissionInfo(roles);
        System.out.println("当前用户权限信息：" + permissions);

        //创建对象，存储当前登录的用户的权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //存储角色
        info.addRoles(roles);
        return info;
    }

    /**
     * @Description: 自定义登录认证方法
     * @Param: [authenticationToken]
     * @Return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: Alex McAvoy
     * @Date: 2022/10/28 22:28
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户身份信息
        String name = token.getPrincipal().toString();
        //调用业务层获取用户信息
        User user = userService.getUserInfoByName(name);
        //判断并将数据完成封装
        if (user != null) {
            System.out.println(user.getPassword());
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    token.getPrincipal(),
                    user.getPassword(),
                    ByteSource.Util.bytes("salt"),
                    token.getPrincipal().toString()
            );
            return info;
        }
        return null;
    }
}
