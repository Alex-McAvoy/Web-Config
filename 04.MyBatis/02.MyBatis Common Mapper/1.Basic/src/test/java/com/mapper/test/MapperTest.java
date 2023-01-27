package com.mapper.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description: Mapper 测试类
 * @Author: Alex McAvoy
 * @Date: 2022/6/24 19:36
 * @Version: 1.0
 **/
public class MapperTest {
    private ApplicationContext iocContainer = new ClassPathXmlApplicationContext("spring-context.xml");

    /**
     * @Description: 测试数据源
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/6/24 19:38
     **/
    @Test
    public void testDataSource() throws SQLException{
        DataSource dataSource = iocContainer.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
