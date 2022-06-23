package com.service;

import com.dao.EmployeeDao;
import com.entities.Employee;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/28 0:24
 * @Version: 1.0
 **/
public class EmployeeService {
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    //显示信息
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    //删除
    public void delete(Integer id) {
        employeeDao.delete(id);
    }

    //员工录入
    public void saveOrUpdate(Employee employee) {
        employeeDao.saveOrUpdate(employee);
    }

    //录入界面用户名验证ajax
    public boolean nameIsValid(String name){
        return employeeDao.getEmployeeByName(name) == null;
    }

    //编辑界面回显
    public Employee get(Integer id) {
        return employeeDao.get(id);
    }
}
