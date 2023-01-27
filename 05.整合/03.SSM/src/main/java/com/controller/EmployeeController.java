package com.controller;

import com.bean.Department;
import com.bean.Employee;
import com.bean.Msg;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 员工控制器
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/4/21 19:30
 **/
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    /**
     * 单个删除批量删除二合一
     * @param ids 员工 id，以 - 分割
     * @return com.bean.Msg
     * @author Alex McAvoy
     * @date 2022/4/22 20:05
     **/
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids) {
        //批量删除
        if (ids.contains("-")) {
            List<Integer> delIds = new ArrayList<>();
            String[] strIds = ids.split("-");
            //组装id的集合
            for (String string : strIds) {
                delIds.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(delIds);
        } else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }


    /**
     * 员工更新方法
     * - 如果直接发送 PUT 求，请求体中有数据，但 Employee 对象封装不上
     * - 原因：
     *  1. 将请求体中的数据，封装一个 map。
     *  2. request.getParameter("empName") 就会从这个 map 中取值。
     *  3. SpringMVC 封装 POJO 对象的时候，会把 POJO 中每个属性的值取出，request.getParameter("xxx");
     *  4. 而 AJAX 发送的 PUT 请求，导致请求体中的数据 request.getParameter("xxx") 拿不到
     *  5. Tomcat 看是 PUT 请求，不会封装请求体中的数据为 map，只有 POST 形式的请求才封装请求体为 map
     * - 解决方案：要能支持直接发送 PUT 之类的请求，还要封装请求体中的数据
     *  1.配置上 HttpPutFormContentFilter，将请求体中的数据解析包装成一个 map
     *  2.request 被重新包装，request.getParameter() 被重写，就会从自己封装的 map中取数据
     * @param employee 员工
     * @param request request
     * @return com.bean.Msg
     * @author Alex McAvoy
     * @date 2022/4/22 14:26
     **/
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    public Msg saveEmp(Employee employee, HttpServletRequest request) {
        System.out.println("请求体中的值：" + request.getParameter("gender"));
        System.out.println("将要更新的员工数据：" + employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }
    
    /** 根据id查询员工
     * @param id 员工 id
     * @return com.bean.Msg
     * @author Alex McAvoy
     * @date 2022/4/22 14:09
     **/
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }
    
    /** 检查用户名是否可用 
     * @param empName 员工名
     * @return com.bean.Msg
     * @author Alex McAvoy
     * @date 2022/4/21 19:06
     **/
    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUser(@RequestParam("empName") String empName) {
        //判断用户名是否是合法的表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!empName.matches(regx)) {
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }

        //数据库用户名重复校验
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "用户名不可用");
        }
    }

    /** 员工保存
     * @param employee 员工
     * @param result BindingResult
     * @return com.bean.Msg
     * @author Alex McAvoy
     * @date 2022/4/21 18:41
     **/
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>(10);

            List<FieldError> errors = result.getFieldErrors();

            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }

    /** Json 分页查询
     * @param pn 当前页
     * @return com.bean.Msg
     * @author Alex McAvoy
     * @date 2022/4/20 23:05
     **/
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //分页查询
        PageHelper.startPage(pn, 5); 
        List<Employee> employeeList = employeeService.getAll();
        
        //设置部门字段
        for (Employee employee : employeeList) {
            int departmentId = employee.getdId();
            String departmentName = departmentService.getName(departmentId).getDeptName();
            employee.setDepartment(new Department(departmentId, departmentName));
        }

        PageInfo<Employee> page = new PageInfo<>(employeeList, 5);

        return Msg.success().add("pageInfo", page);
    }

    /** PageHelper 分页查询
     * @param pn 当前页
     * @param model 视图
     * @return java.lang.String
     * @author Alex McAvoy
     * @date 2022/4/20 20:12
     **/
//    @RequestMapping("/emps")
    public String getEmps(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        //分页查询
        PageHelper.startPage(pn, 5);

        List<Employee> emps = employeeService.getAll();

        //设置部门字段
        for (Employee employee : emps) {
            int departmentId = employee.getdId();
            String departmentName = departmentService.getName(departmentId).getDeptName();
            employee.setDepartment(new Department(departmentId, departmentName));
        }

        PageInfo<Employee> page = new PageInfo<>(emps, 5);
        model.addAttribute("pageInfo", page);

        return "list";
    }

}
