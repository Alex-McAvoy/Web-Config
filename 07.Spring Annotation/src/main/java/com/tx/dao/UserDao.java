package com.tx.dao;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/7/1 22:51
 * @Version: 1.0
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional //通知 Spring 方法是事务方法
    public void insert() {
        String sql = "INSERT INTO `users`(username,password) VALUES(?,?)";
        String username = UUID.randomUUID().toString().substring(0,5);
        jdbcTemplate.update(sql,username,"123456");
        System.out.println("插入完成");
        int i = 5/0;
    }
}
