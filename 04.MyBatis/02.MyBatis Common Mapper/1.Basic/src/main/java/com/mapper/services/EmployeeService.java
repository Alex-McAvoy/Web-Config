package com.mapper.services;

import com.mapper.beans.Employee;
import com.mapper.mappers.EmployeeMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description: Employee 对应的 Service
 * @Author: Alex McAvoy
 * @Date: 2022/6/24 20:05
 * @Version: 1.0
 **/
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getOne(Employee employeeQueryCondition) {
        return employeeMapper.selectOne(employeeQueryCondition);
    }

    public List<Employee> getAll() {
        return employeeMapper.selectAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public boolean isExists(Integer id) {
        return employeeMapper.existsWithPrimaryKey(id);
    }

    public void saveEmployee(Employee employee) {
        employeeMapper.insert(employee);
    }

    public void saveEmployeeSelective(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public void updateEmployeeSelective(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeMapper.delete(employee);
    }

    public void deleteEmployeeById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public List<Employee> getEmployeeListByExample(Example example) {
        return employeeMapper.selectByExample(example);
    }

    public List<Employee> getEmployeeListByRowBounds(Employee employee, RowBounds rowBounds) {
        return employeeMapper.selectByRowBounds(employee,rowBounds);
    }
}
