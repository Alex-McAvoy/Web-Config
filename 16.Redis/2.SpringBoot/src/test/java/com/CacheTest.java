package com;

import com.bean.Department;
import com.bean.Employee;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: Redis 缓存测试
 * @Author: Alex McAvoy
 * @Date: 2022/10/26 16:56
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Autowired(required = false)
    EmployeeService employeeService;

    @Autowired(required = false)
    DepartmentService departmentService;

    /**
     * @Description: 查询测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/26 16:59
     **/
    @Test
    public void employeeSelectTest() {
        List<Employee> employees = employeeService.getEmployees();
        List<Department> departments = departmentService.getDepartments();

        System.out.println(employees);
        System.out.println(departments);
    }

    /**
     * @Description: 插入测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/26 17:10
     **/
    @Test
    public void employeeInsertTest() {
        Employee employee = new Employee();
        employee.setLastName("123132");
        employeeService.insertEmployee(employee);
    }

    /**
     * @Description: 更新测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/26 17:10
     **/
    @Test
    public void employeeUpdateTest() {
        Employee employee = employeeService.getEmployeeById(6);
        employee.setEmail("1231231");
        employeeService.updateEmployee(employee);
    }
    
    /**
     * @Description: 删除测试
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/26 17:12
     **/
    @Test
    public void employeeDeleteTest() {
        employeeService.deleteEmployeeById(6);
    }

}
