package com.service;

import com.bean.Employee;

import java.util.List;

/**
 * Employee 的 Service 接口
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/11/10 21:45
 **/
public interface EmployeeService {
    /**
     * 通过 id 获取员工
     *
     * @param id 员工id
     * @return com.bean.Employee
     * @author Alex McAvoy
     * @date 2022/11/10 21:48
     **/
    public Employee getEmployeeById(Integer id);

    /**
     * 通过 id 删除员工
     *
     * @param id 员工id
     * @author Alex McAvoy
     * @date 2022/11/10 21:48
     **/
    public void deleteEmployeeById(Integer id);

    /**
     * 更新员工
     *
     * @param employee 员工
     * @author Alex McAvoy
     * @date 2022/11/10 21:48
     **/
    public void updateEmployee(Employee employee);

    /**
     * 插入员工
     *
     * @param employee 员工
     * @author Alex McAvoy
     * @date 2022/11/10 21:49
     **/
    public void insertEmployee(Employee employee);

    /**
     * 获取所有员工
     *
     * @return java.util.List<com.bean.Employee>
     * @author Alex McAvoy
     * @date 2022/11/10 21:49
     **/
    public List<Employee> getEmployeeList();
}
