package com.main.controller;

import com.main.bean.Employee;
import com.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/11 0:09
 * @Version: 1.0
 **/
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/getEmployees")
    public String getEmployees(Map<String,Object> map) {
        List<Employee> employees = employeeService.getEmployees();
        map.put("employees",employees);
        return "list";
    }
}
