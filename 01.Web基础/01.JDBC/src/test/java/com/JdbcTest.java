package com;


import com.bean.Person;
import com.utils.JdbcUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class JdbcTest {
    JdbcUtils jdbcUtils = new JdbcUtils();

    @Test
    public void test() {
        jdbcUtils.getConn();

        //增
//        try {
//            String sql = "INSERT INTO name_age(name,age) VALUES(?, ?);";
//            jdbcUtils.update(sql,"job","15");
//            System.out.println("插入成功");
//        } catch (SQLException e) {
//            System.out.println("插入失败");
//            e.printStackTrace();
//        }

        //删
//        try {
//            String sql = "DELETE FROM name_age WHERE id = ?;";
//            jdbcUtils.update(sql,4);
//            System.out.println("删除成功");
//        } catch (SQLException e) {
//            System.out.println("删除失败");
//            e.printStackTrace();
//        }

        //改
//        try {
//            String sql = "UPDATE name_age SET age = ? WHERE id = ?;";
//            jdbcUtils.update(sql, 15, 3);
//            System.out.println("修改成功");
//        } catch (SQLException e) {
//            System.out.println("修改失败");
//            e.printStackTrace();
//        }

        //查
        try {
            String sql = "SELECT * FROM name_age WHERE age > ?;";
            List<Object> list = jdbcUtils.query(new Person(), sql, 10);
            System.out.println(list);
            System.out.println("查询成功");
        } catch (SQLException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
        jdbcUtils.closeDB();
    }
}
