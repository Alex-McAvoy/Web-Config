package com.main.dao;

import com.main.bean.Employee;
import java.util.List;
/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/30 16:53
 * @Version: 1.0
 **/
public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);
    public List<Employee> getEmployees();
}
