package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bean.User;
import com.mapper.UserMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户类 Service 的实现类
 * @author Alex McAvoy
 * @date 2022/10/28 22:20
 * @version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param name 用户名
     * @return com.bean.User
     * @author Alex McAvoy
     * @date 2022/10/28 22:21
     **/
    @Override
    public User getUserInfoByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据用户查询角色信息
     * @param principal 权限
     * @return java.util.List<java.lang.String>
     * @author Alex McAvoy
     * @date 2022/10/29 14:23
     **/
    @Override
    public List<String> getUserRoleInfo(String principal) {
        return userMapper.getUserRoleInfoMapper(principal);
    }
    
    /**
     * 获取用户角色的权限信息
     * @param roles 角色
     * @return java.util.List<java.lang.String>
     * @author Alex McAvoy
     * @date 2022/10/29 14:24
     **/
    @Override
    public List<String> getUserPermissionInfo(List<String> roles) {
        return userMapper.getUserPermissionInfoMapper(roles);
    }
}
