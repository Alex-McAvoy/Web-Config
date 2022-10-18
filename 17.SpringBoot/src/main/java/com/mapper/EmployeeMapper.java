package com.mapper;

import com.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: Employee 类的 Mapper
 * @Author: Alex McAvoy
 * @Date: 2022/9/29 18:16
 * @Version: 1.0
 **/
@Mapper
public interface EmployeeMapper {
//    @Select("SELECT * FROM employee WHERE id=#{id}")
    public Employee getEmployeeById(Integer id);

//    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmployeeById(Integer id);

//    @Update("UPDATE employee SET last_name=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmployee(Employee employee);

//    @Select("SELECT * FROM employee")
    public List<Employee> getEmployees();

//    @Options(useGeneratedKeys = true,keyProperty = "id") //自增主键
//    @Insert("INSERT INTO employee(last_name,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

}
