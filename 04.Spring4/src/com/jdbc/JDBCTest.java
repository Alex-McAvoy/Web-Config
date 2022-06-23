package com.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/26 3:01
 * @Version: 1.0
 **/
public class JDBCTest {
    private ApplicationContext ctx = null;
    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    {
        ctx = new ClassPathXmlApplicationContext("/config/jdbcContext.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        userDao = (UserDao) ctx.getBean("userDao");
        namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
    }

    /**
     * @Description: 测试 NameParameterJdbcTemplate，可以为SQL中的参数起自定义名
     *              好处：若有多个参数，则不用去对应位置，直接对应参数名，便于维护
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 4:24
     **/
    @Test
    public void testNameParameterJdbcTemplate() {
        String sql = "INSERT INTO test_users(user,password) VALUES(:user,:password);";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("user","jackson");
        paramMap.put("password","789215");

        namedParameterJdbcTemplate.update(sql,paramMap);
    }

    /**
     * @Description: 测试数据源是否连接成功
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 3:20
     **/
    @Test
    public void TestDataSource() {

        //数据源
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: 执行 UPDATE INSERT DELETE
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 3:20
     **/
    @Test
    public void testUpdate() {
        String sql = "UPDATE test_users SET user = ? WHERE id = ?;";
        jdbcTemplate.update(sql, "Alex", "1");
    }

    /**
     * @Description: 测试批量 INSERT UPDATE DELETE
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 3:23
     **/
    @Test
    public void testBatchUpdate() {
        String sql = "INSERT INTO test_users(user,password) VALUES(?,?);";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"tom", "123456"});
        batchArgs.add(new Object[]{"jack", "654321"});

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * @Description: 从数据库中获取一条记录，实际得到对应的一个对象
     *              其中 rowMapper 用于映射结果集的行
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 3:35
     **/
    @Test
    public void testQueryForObject() {
        String sql = "SELECT * FROM test_users WHERE id = ?;";
        RowMapper<UserBean> rowMapper = new BeanPropertyRowMapper<>(UserBean.class);
        UserBean user = jdbcTemplate.queryForObject(sql,rowMapper,1);
        System.out.println(user);
    }

    /**
     * @Description: 查询实体类的集合
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 3:46
     **/
    @Test
    public void testQueryList() {
        String sql = "SELECT * FROM test_users WHERE id > ?";
        RowMapper<UserBean> rowMapper = new BeanPropertyRowMapper<>(UserBean.class);
        List<UserBean> users = jdbcTemplate.query(sql,rowMapper,2);
        System.out.println(users);
    }

    /**
     * @Description: 获取数据表的行数
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 3:50
     **/
    @Test
    public void testQueryRowValue() {
        String sql = "SELECT count(id) FROM test_users;";
        long count = jdbcTemplate.queryForObject(sql,Long.class);
        System.out.println(count);
    }

    /**
     * @Description: 测试开发时使用的 Dao
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/2/26 4:00
     **/
    @Test
    public void testDao() {
        UserBean user = userDao.get(1);
        System.out.println(user);
    }
}
