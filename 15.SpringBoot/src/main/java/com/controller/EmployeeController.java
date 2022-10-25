package com.controller;

import com.bean.Department;
import com.bean.Employee;
import com.mapper.DepartmentMapper;
import com.mapper.EmployeeMapper;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 员工管理控制器
 * @Author: Alex McAvoy
 * @Date: 2022/9/23 16:25
 * @Version: 1.0
 **/
@Controller
public class EmployeeController {
    @Autowired(required = false)
    EmployeeService employeeService;
    @Autowired(required = false)
    DepartmentService departmentService;

    /**
     * @Description: 查询所有员工返回列表页面
     * @Param: [model]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/23 16:42
     **/
    @GetMapping("/employees")
    public String list(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        List<Department> departments = departmentService.getDepartments();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        //拼接：classpath:templates/ + ... + .html
        return "employee/list";
    }

    /**
     * @Description: 跳转到员工添加页面
     * @Param: [model]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/23 17:02
     **/
    @GetMapping("/employee")
    public String toAddPage(Model model) {
        List<Department> departments = departmentService.getDepartments();
        model.addAttribute("departments", departments);
        return "employee/add";
    }

    /**
     * @Description: 员工添加
     * @Param: [employee]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/23 17:06
     **/
    @PostMapping("/employee")
    public String addEmployee(Employee employee) {
        // SpringMVC 自动将请求参数和入参对象的属性进行一一绑定
        // 要求请求参数的名字和 JavaBean 入参的对象里面的属性名一样
        employeeService.insertEmployee(employee);
        return "redirect:/employees"; // 重定向到员工管理页
    }

    /**
     * @Description: 跳转到员工修改页面
     * @Param: [id, model]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/23 17:22
     **/
    @GetMapping("/employee/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        List<Department> departments = departmentService.getDepartments();
        model.addAttribute("departments", departments);

        return "/employee/add"; //add.html是一修改添加二合一的页面
    }

    /**
     * @Description: 员工修改
     * @Param: [employee]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/23 18:53
     **/
    @PutMapping("/employee")
    public String updateEmployee(Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    /**
     * @Description: 员工删除
     * @Param: [id]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/9/23 19:06
     **/
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

}
