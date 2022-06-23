package com.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 开发时使用的方式
 * @Author: Alex McAvoy
 * @Date: 2022/2/26 3:50
 * @Version: 1.0
 **/
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * @Description: 实例
     * @Param: [id]
     * @Return: com.jdbc.UserBean
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 3:51
     **/
    public UserBean get(Integer id) {
        String sql = "SELECT * FROM test_users WHERE id = ?;";
        RowMapper<UserBean> rowMapper = new BeanPropertyRowMapper<>(UserBean.class);
        UserBean user = jdbcTemplate.queryForObject(sql,rowMapper,id);
        return user;
    }
}
