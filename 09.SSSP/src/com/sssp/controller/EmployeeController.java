package com.sssp.controller;

import com.sssp.entity.Employee;
import com.sssp.service.DepartmentService;
import com.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/3/15 15:14
 * @Version: 1.0
 **/
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * @Description: 分页显示
     * @Param: [pageNumberStr, map]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/15 17:33
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
     * @Description: 添加页面
     * @Param: [map]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/15 18:37
     **/
    @RequestMapping(value = "/input",method = RequestMethod.GET)
    public String input(Map<String,Object> map) {
        map.put("departments",departmentService.getAll());
        map.put("employee",new Employee());
        return "input";
    }

    /**
     * @Description: 添加用户
     * @Param: []
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/15 21:05
     **/
    @RequestMapping(value = "/input",method = RequestMethod.POST)
    public String save(Employee employee) {
        employeeService.save(employee);
        return "redirect:/show_list";
    }

    /**
     * @Description: 用户名 ajax 验证
     * @Param: [name]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/15 20:53
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
     * @Description: 修改用户回显
     * @Param: [id, map]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/16 16:51
     **/
    @RequestMapping(value = "/input/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id,Map<String,Object> map) {
        Employee employee = employeeService.get(id);
        map.put("employee",employee);
        map.put("departments",departmentService.getAll());
        return "input";
    }

    /**
     * @Description: 修改用户
     * @Param: [id, map]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/3/16 17:10
     **/
    @RequestMapping(value = "/input/{id}",method = RequestMethod.PUT)
    public String update(@PathVariable("id") Integer id,Map<String,Object> map) {
        Employee employee = employeeService.get(id);
        map.put("employee",employee);
        map.put("departments",departmentService.getAll());
        return "redirect:/show_list";
    }

    /**
     * @Description: 修改用户修改后回显
     * @Param: [id, map]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/16 17:16
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
