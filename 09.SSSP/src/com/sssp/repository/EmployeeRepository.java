package com.sssp.repository;

import com.sssp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.jar.JarEntry;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/15 15:06
 * @Version: 1.0
 **/
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee getByName(String name);
}
