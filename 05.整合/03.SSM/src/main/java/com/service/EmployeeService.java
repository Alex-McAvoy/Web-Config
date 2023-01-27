package com.service;

import com.bean.Employee;
import com.bean.EmployeeExample;
import com.dao.EmployeeMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 员工 Service
 * @author Alex McAvoy
 * @date 2022/4/20 19:32
 * @version 1.0
 **/
@Service
public class EmployeeService {
    @Autowired(required = false)
    EmployeeMapper employeeMapper;

    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

}
