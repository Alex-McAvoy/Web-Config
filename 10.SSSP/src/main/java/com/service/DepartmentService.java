package com.service;

import com.entity.Department;
import com.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  部门 Service
 * @author Alex McAvoy
 * @date 2022/3/15 18:32
 * @version 1.0
 **/
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }
}
