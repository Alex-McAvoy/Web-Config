package com;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @Description: 环境测试
 * @Param:
 * @Return:
 * @Author: Alex McAvoy
 * @Date: 2022/10/25 16:33
 **/
public class TestMP {
    private ApplicationContext iocContext = new
            ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testEnvironment() throws Exception{
        DataSource ds = iocContext.getBean("dataSource",DataSource.class);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
