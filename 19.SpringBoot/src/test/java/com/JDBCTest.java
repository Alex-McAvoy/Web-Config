package com;

import com.bean.Department;
import com.bean.Employee;
import com.mapper.DepartmentMapper;
import com.mapper.EmployeeMapper;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description: JDBC 测试类
 * @Author: Alex McAvoy
 * @Date: 2022/9/29 13:07
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testQuery() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM department");
        System.out.println(list);
    }

    @Autowired(required = false)
    DepartmentService departmentService;

    @Test
    public void testMyBatisDepartment() {

        Department department = departmentService.getDepartmentById(1);
        System.out.println(department);
        department.setDepartmentName("xxxx");
        departmentService.updateDepartment(department);


        Department department1 = new Department();
        department1.setDepartmentName("afasf");
        departmentService.insertDepartment(department1);

        System.out.println(departmentService.getDepartments());

        departmentService.deleteDepartmentById(4);

    }

    @Autowired(required = false)
    EmployeeService employeeService;

    @Test
    public void testMyBatisEmployee() {

        Employee employee = employeeService.getEmployeeById(1);
        System.out.println(employee);
//
//        Department department = departmentService.getDepartmentById(1);
//        System.out.println(department);
    }

}
