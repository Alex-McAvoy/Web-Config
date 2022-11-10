package com.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.bean.Employee;
import com.dao.EmployeeDao;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Employee 的 Service 实现类
 * @author Alex McAvoy
 * @date 2022/9/29 21:08
 * @version 1.0
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired(required = false)
    EmployeeDao employeeDao;

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateById(employee);
    }

    @Override
    public void insertEmployee(Employee employee) {
        employeeDao.insert(employee);
    }

    @Override
    public List<Employee> getEmployeeList() {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        return employeeDao.selectList(queryWrapper);
    }

}
