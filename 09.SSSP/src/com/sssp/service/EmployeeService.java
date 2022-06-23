package com.sssp.service;

import com.sssp.entity.Employee;
import com.sssp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/15 15:11
 * @Version: 1.0
 **/
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNumber, int pageSize) {
        PageRequest pageable = new PageRequest(pageNumber - 1, pageSize); //分页索引从0开始
        return employeeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Employee getByEmployee(String name) {
        return employeeRepository.getByName(name);
    }

    @Transactional
    public void save(Employee employee) {
        if(employee.getId() == null) { //新建员工时
            employee.setCreateTime(new Date());
        }
        employeeRepository.saveAndFlush(employee);
    }

    @Transactional
    public Employee get(Integer id) {
        return employeeRepository.findOne(id);
    }

    @Transactional
    public void delete(Integer id) {
        employeeRepository.delete(id);
    }
}
