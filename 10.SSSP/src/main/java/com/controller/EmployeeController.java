package com.controller;

import com.entity.Employee;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *  员工控制器
 * @author Alex McAvoy
 * @date 2022/3/15 15:14
 * @version 1.0
 **/
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    /**
     *  分页显示
     * @param pageNumberStr 分页号
	 * @param map model
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/15 17:33
     **/
    @RequestMapping("/show_list")
    public String list(@RequestParam(value = "pageNumber", required = false, defaultValue = "1")
                               String pageNumberStr, Map<String, Object> map) {
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(pageNumberStr);
            if (pageNumber < 1) {
                pageNumber = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Page<Employee> page = employeeService.getPage(pageNumber,5);
        map.put("page",page);
        return "list";
    }

    /**
     *  添加页面
     * @param map model
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/15 18:37
     **/
    @RequestMapping(value = "/input",method = RequestMethod.GET)
    public String input(Map<String,Object> map) {
        map.put("departments",departmentService.getAll());
        map.put("employee",new Employee());
        return "input";
    }

    /**
     *  添加用户
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/15 21:05
     **/
    @RequestMapping(value = "/input",method = RequestMethod.POST)
    public String save(Employee employee) {
        employeeService.save(employee);
        return "redirect:/show_list";
    }

    /**
     *  用户名 ajax 验证
     * @param name 用户名
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/15 20:53
     **/
    @ResponseBody
    @RequestMapping(value = "/ajaxValidateName",method = RequestMethod.POST)
    public String validateName(@RequestParam(value = "name",required = true) String name){
        Employee employee = employeeService.getByEmployee(name);
        if(employee == null)
            return "0";
        else
            return "1";
    }

    /**
     *  修改用户回显
     * @param id 用户id
	 * @param map model
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/16 16:51
     **/
    @RequestMapping(value = "/input/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id,Map<String,Object> map) {
        Employee employee = employeeService.get(id);
        map.put("employee",employee);
        map.put("departments",departmentService.getAll());
        return "input";
    }

    /**
     *  修改用户
     * @param id 用户id
	 * @param map model
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/3/16 17:10
     **/
    @RequestMapping(value = "/input/{id}",method = RequestMethod.PUT)
    public String update(@PathVariable("id") Integer id,Map<String,Object> map) {
        Employee employee = employeeService.get(id);
        map.put("employee",employee);
        map.put("departments",departmentService.getAll());
        return "redirect:/show_list";
    }

    /**
     *  修改用户修改后回显
     * @param id 用户id
	 * @param map model
     * @return void
     * @author Alex McAvoy
     * @date 2022/3/16 17:16
     **/
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false) Integer id,
                            Map<String,Object> map) {
        if(id != null) {
            Employee employee = employeeService.get(id);
            employee.setDepartment(null);
            map.put("employee",employee);
        }
    }

    @RequestMapping(value = "/input/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeService.delete(id);
        return "redirect:/show_list";
    }
}
