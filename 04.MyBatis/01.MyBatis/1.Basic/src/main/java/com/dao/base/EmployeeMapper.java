package com.dao.base;

import com.bean.base.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployeeById(Integer id);

    public Employee getEmployeeByIdAndName(Integer id, String name);
    public Employee getEmployeeByIdAndGender(@Param("id") Integer id,@Param("gender") String gender);
    public Employee getEmployeeByMap(Map<String, Object> map);

    public List<Employee> getEmployeeByNameLike(String name);

    public Map<String, Object> getEmployeeByIdReturnMap(Integer id);
    /** @MapKey 用来通知 MyBatis 封装 Map 时使用哪个属性 **/
    @MapKey("id")
    public Map<Integer, Employee> getEmployeeByNameReturnMap(String name);
}
