package com.main.service;

import com.main.bean.Employee;
import com.main.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/11 0:11
 * @Version: 1.0
 **/
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getEmployees(){
        return employeeMapper.getEmployees();
    }
}
