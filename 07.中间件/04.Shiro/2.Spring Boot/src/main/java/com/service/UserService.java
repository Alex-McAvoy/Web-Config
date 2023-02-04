package com.service;

import com.bean.User;

import java.util.List;

/**
 * 用户类 Service
 * @author Alex McAvoy
 * @date 2022/10/28 22:17
 * @version 1.0
 **/
public interface UserService {
    User getUserInfoByName(String name);
    List<String> getUserRoleInfo(String principal);
    List<String> getUserPermissionInfo(List<String> roles);
}
