package com.dao.cascade;

import com.bean.cascade.Department;


public interface DepartmentMapper {
    public Department getDepartmentById(Integer id);
    public Department getDepartmentByUnionQuery1(Integer id);
    public Department getDepartmentByUnionQuery2(Integer id);
}
