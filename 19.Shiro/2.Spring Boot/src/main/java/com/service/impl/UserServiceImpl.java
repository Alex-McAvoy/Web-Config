package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bean.User;
import com.mapper.UserMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 用户类 Service 的实现类
 * @Author: Alex McAvoy
 * @Date: 2022/10/28 22:20
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 用户登录
     * @Param: [name]
     * @Return: com.bean.User
     * @Author: Alex McAvoy
     * @Date: 2022/10/28 22:21
     **/
    @Override
    public User getUserInfoByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return userMapper.selectOne(wrapper);
    }

    /**
     * @Description: 根据用户查询角色信息
     * @Param: [principal]
     * @Return: java.util.List<java.lang.String>
     * @Author: Alex McAvoy
     * @Date: 2022/10/29 14:23
     **/
    @Override
    public List<String> getUserRoleInfo(String principal) {
        return userMapper.getUserRoleInfoMapper(principal);
    }
    
    /**
     * @Description: 获取用户角色的权限信息
     * @Param: [roles]
     * @Return: java.util.List<java.lang.String>
     * @Author: Alex McAvoy
     * @Date: 2022/10/29 14:24
     **/
    @Override
    public List<String> getUserPermissionInfo(List<String> roles) {
        return userMapper.getUserPermissionInfoMapper(roles);
    }
}
