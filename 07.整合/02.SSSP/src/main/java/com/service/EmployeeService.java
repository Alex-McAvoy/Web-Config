package com.service;

import com.entity.Employee;
import com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *  员工 Service
 * @author Alex McAvoy
 * @date 2022/3/15 15:11
 * @version 1.0
 **/
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNumber, int pageSize) {
        //分页索引从0开始
        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Employee getByEmployee(String name) {
        return employeeRepository.getByName(name);
    }

    @Transactional
    public void save(Employee employee) {
		//新建员工时
        if(employee.getId() == null) { 
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
