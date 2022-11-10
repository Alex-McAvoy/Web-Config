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
    public Employee getEmployeeById(Integer id);

    public void deleteEmployeeById(Integer id);

    public void updateEmployee(Employee employee);

    public List<Employee> getEmployees();

    public void insertEmployee(Employee employee);

}
