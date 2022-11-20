package com.repository;

import com.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 员工 dao
 * @author Alex McAvoy
 * @date 2022/3/15 15:06
 * @version 1.0
 **/
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee getByName(String name);
}
