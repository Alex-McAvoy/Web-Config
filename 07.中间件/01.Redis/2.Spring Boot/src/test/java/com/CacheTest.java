package com;

import com.bean.Department;
import com.bean.Employee;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.service.Impl.DepartmentServiceImpl;
import com.service.Impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Redis 缓存测试
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/26 16:56
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Autowired(required = false)
    EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    DepartmentServiceImpl departmentServiceImpl;

    /**
     * 查询测试
     *
     * @author Alex McAvoy
     * @date 2022/11/10 22:19
     **/
    @Test
    public void employeeSelectTest() {
        List<Employee> employees = employeeServiceImpl.getEmployeeList();
        List<Department> departments = departmentServiceImpl.getDepartmentList();

        System.out.println(employees);
        System.out.println(departments);
    }

    /**
     * 插入测试
     *
     * @author Alex McAvoy
     * @date 2022/11/10 22:19
     **/
    @Test
    public void employeeInsertTest() {
        Employee employee = new Employee();
        employee.setLastName("123132");
        employeeServiceImpl.insertEmployee(employee);
    }

    /**
     * 更新测试
     *
     * @author Alex McAvoy
     * @date 2022/11/10 22:19
     **/
    @Test
    public void employeeUpdateTest() {
        Employee employee = employeeServiceImpl.getEmployeeById(6);
        employee.setEmail("1231231");
        employeeServiceImpl.updateEmployee(employee);
    }

    /**
     * 删除测试
     *
     * @author Alex McAvoy
     * @date 2022/11/10 22:19
     **/
    @Test
    public void employeeDeleteTest() {
        employeeServiceImpl.deleteEmployeeById(6);
    }

}
