package com.mapper;

import com.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: Department 类的 Mapper
 * @Author: Alex McAvoy
 * @Date: 2022/9/29 17:50
 * @Version: 1.0
 **/
@Mapper
public interface DepartmentMapper {
    public Department getDepartmentById(Integer id);

    public void deleteDepartmentById(Integer id);

    public List<Department> getDepartments();

    public void updateDepartment(Department department);

    public void insertDepartment(Department department);
}
