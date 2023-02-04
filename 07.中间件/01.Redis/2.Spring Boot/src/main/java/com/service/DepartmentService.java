package com.service;

import com.bean.Department;

import java.util.List;

/**
 * Department 的 Service 接口
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/11/10 21:46
 **/
public interface DepartmentService {
    /**
     * 通过 id 获取部门
     *
     * @param id 部门 id
     * @return com.bean.Department
     * @author Alex McAvoy
     * @date 2022/11/10 21:50
     **/
    public Department getDepartmentById(Integer id);

    /**
     * 获取所有部门
     *
     * @return java.util.List<com.bean.Department>
     * @author Alex McAvoy
     * @date 2022/11/10 21:50
     **/
    public List<Department> getDepartmentList();
}
