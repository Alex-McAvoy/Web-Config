package com.service;

import com.bean.Employee;
import com.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: Employee 的 Service 层
 * @Author: Alex McAvoy
 * @Date: 2022/9/29 21:08
 * @Version: 1.0
 **/
@Service
public class EmployeeService {
    @Autowired(required = false)
    EmployeeMapper employeeMapper;

    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    public void deleteEmployeeById(Integer id) {
        employeeMapper.deleteEmployeeById(id);
    }

    public Employee updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }

    public void insertEmployee(Employee employee) {
        employeeMapper.insertEmployee(employee);
    }
}
