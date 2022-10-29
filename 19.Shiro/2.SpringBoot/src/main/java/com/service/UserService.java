package com.service;

import com.bean.User;

import java.util.List;

/**
 * @Description: 用户类 Service
 * @Author: Alex McAvoy
 * @Date: 2022/10/28 22:17
 * @Version: 1.0
 **/
public interface UserService {
    User getUserInfoByName(String name);
    List<String> getUserRoleInfo(String principal);
    List<String> getUserPermissionInfo(List<String> roles);
}
