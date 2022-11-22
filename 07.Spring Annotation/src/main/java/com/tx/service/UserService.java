package com.tx.service;

import com.tx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/7/1 22:51
 * @version 1.0
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void insertUser() {
        userDao.insert();
        System.out.println("插入完成");

    }
}
