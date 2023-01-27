package com.controller;

import com.bean.Department;
import com.bean.Employee;
import com.service.Impl.DepartmentServiceImpl;
import com.service.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理控制器
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/9/23 16:25
 **/
@Controller
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeServiceImpl;
    @Autowired
    DepartmentServiceImpl departmentServiceImpl;

    /**
     * 查询所有员工返回列表页面
     *
     * @param model 视图
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/9/23 16:42
     **/
    @GetMapping("/employees")
    public String list(Model model) {
        List<Employee> employees = employeeServiceImpl.getEmployeeList();
        List<Department> departments = departmentServiceImpl.getDepartmentList();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        //拼接：classpath:templates/ + ... + .html
        return "employee/list";
    }

    /**
     * 跳转到员工添加页面
     *
     * @param model 视图
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/9/23 17:02
     **/
    @GetMapping("/employee")
    public String toAddPage(Model model) {
        List<Department> departments = departmentServiceImpl.getDepartmentList();
        model.addAttribute("departments", departments);
        return "employee/add";
    }

    /**
     * 员工添加
     *
     * @param employee 员工
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/9/23 17:06
     **/
    @PostMapping("/employee")
    public String addEmployee(Employee employee) {
        employeeServiceImpl.insertEmployee(employee);
        // 重定向到员工管理页
        return "redirect:/employees";
    }

    /**
     * 跳转到员工修改页面
     *
     * @param id    员工id
     * @param model 视图
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/9/23 17:22
     **/
    @GetMapping("/employee/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeServiceImpl.getEmployeeById(id);
        model.addAttribute("employee", employee);

        List<Department> departments = departmentServiceImpl.getDepartmentList();
        model.addAttribute("departments", departments);

        //add.html是一修改添加二合一的页面
        return "employee/add";
    }

    /**
     * 员工修改
     *
     * @param employee 员工
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/11/10 17:21
     **/
    @PutMapping("/employee")
    public String updateEmployee(Employee employee) {
        employeeServiceImpl.updateEmployee(employee);
        return "redirect:/employees";
    }

    /** 员工删除
     * @param id 员工id
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/9/23 19:06
     **/
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeServiceImpl.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
