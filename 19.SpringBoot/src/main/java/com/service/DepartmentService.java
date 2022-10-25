package com.service;

import com.bean.Department;
import com.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: Department 的 Service 层
 * @Author: Alex McAvoy
 * @Date: 2022/9/29 21:09
 * @Version: 1.0
 **/
//@CacheConfig(cacheNames = "department")
@Service
public class DepartmentService implements Serializable {
    @Autowired(required = false)
    DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = "department", key = "#department.id")
    public Department getDepartmentById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @CacheEvict(cacheNames = "department",key = "#id")
    public void deleteDepartmentById(Integer id) {
        departmentMapper.deleteDepartmentById(id);
    }

    @CachePut(cacheNames = "department", key = "#department.id")
    public Department updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
        return department;
    }

    @CachePut(cacheNames = "departments", keyGenerator = "departmentsKeyGenerator")
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }

    public void insertDepartment(Department department) {
        departmentMapper.insertDepartment(department);
    }
}
