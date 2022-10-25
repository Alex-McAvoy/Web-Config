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
//    @Select("SELECT * FROM department WHERE id=#{id}")
    public Department getDepartmentById(Integer id);

//    @Delete("DELETE FROM department WHERE id=#{id}")
    public void deleteDepartmentById(Integer id);

//    @Select("SELECT * FROM department")
    public List<Department> getDepartments();

//    @Update("UPDATE department SET department_name=#{departmentName} WHERE id=#{id}")
    public void updateDepartment(Department department);

//    @Options(useGeneratedKeys = true,keyProperty = "id")
//    @Insert("INSERT INTO department(department_name) VALUES(#{departmentName})")
    public void insertDepartment(Department department);

}
