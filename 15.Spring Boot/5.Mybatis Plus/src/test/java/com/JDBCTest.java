package com;

import com.bean.Department;
import com.bean.Employee;
import com.service.Impl.DepartmentServiceImpl;
import com.service.Impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC 测试类
 * @author Alex McAvoy
 * @date 2022/9/29 13:07
 * @version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCTest {
    @Autowired
    DataSource dataSource;

    /** 测试数据源
     * @author Alex McAvoy
     * @date 2022/09/30 20:56
     **/
    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Autowired(required = false)
    DepartmentServiceImpl departmentServiceImpl;

    /** 测试 Department 的 Service
     * @author Alex McAvoy
     * @date 2022/11/10 20:57
     **/
    @Test
    public void testMyBatisDepartment() {
        Department department = departmentServiceImpl.getDepartmentById(1);
        System.out.println(department);

        System.out.println(departmentServiceImpl.getDepartmentList());

    }

    @Autowired(required = false)
    EmployeeServiceImpl employeeServiceImpl;

    /** 测试 Employee 的 Service
     * @author Alex McAvoy
     * @date 2022/11/10 20:57
     **/
    @Test
    public void testMyBatisEmployee() {

        Employee employee = employeeServiceImpl.getEmployeeById(1);
        System.out.println(employee);

//        Department department = departmentService.getDepartmentById(1);
//        System.out.println(department);
    }

}
