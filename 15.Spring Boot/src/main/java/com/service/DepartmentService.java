package com.service;

import com.bean.Department;
import com.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: Department 的 Service 层
 * @Author: Alex McAvoy
 * @Date: 2022/9/29 21:09
 * @Version: 1.0
 **/
@Service
public class DepartmentService implements Serializable {
    @Autowired(required = false)
    DepartmentMapper departmentMapper;

    public Department getDepartmentById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    public void deleteDepartmentById(Integer id) {
        departmentMapper.deleteDepartmentById(id);
    }

    public Department updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
        return department;
    }

    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }

    public void insertDepartment(Department department) {
        departmentMapper.insertDepartment(department);
    }
}
