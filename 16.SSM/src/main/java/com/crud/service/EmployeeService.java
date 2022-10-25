package com.crud.service;

import com.crud.bean.Employee;
import com.crud.bean.EmployeeExample;
import com.crud.dao.EmployeeMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/20 19:32
 * @Version: 1.0
 **/
@Service
public class EmployeeService {
    @Autowired(required = false)
    EmployeeMapper employeeMapper;

    /**
     * @Description: 单个删除
     * @Param: [id]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/22 20:05
     **/
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Description: 批量删除
     * @Param: [ids]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/22 20:06
     **/
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }

    /**
     * @Description: 员工更新
     * @Param: [employee]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/22 14:19
     **/
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * @Description: 按照员工id查询员工
     * @Param: [id]
     * @Return: com.crud.bean.Employee
     * @Author: Alex McAvoy
     * @Date: 2022/4/22 14:09
     **/
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * @Description: 检验用户名是否可用
     * @Param: [empName]
     * @Return: boolean
     * @Author: Alex McAvoy
     * @Date: 2022/4/21 19:06
     **/
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    /**
     * @Description: 员工保存
     * @Param: [employee]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/21 18:42
     **/
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * @Description: 查询所有员工
     * @Param: []
     * @Return: com.sun.tools.javac.util.List<com.crud.bean.Employee>
     * @Author: Alex McAvoy
     * @Date: 2022/4/20 19:35
     **/
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

}
