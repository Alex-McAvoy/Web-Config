package com.crud.controller;

import com.crud.bean.Department;
import com.crud.bean.Employee;
import com.crud.bean.Msg;
import com.crud.dao.DepartmentMapper;
import com.crud.service.DepartmentService;
import com.crud.service.EmployeeService;
import com.github.pagehelper.Page;
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
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/20 19:30
 * @Version: 1.0
 **/
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    /**
     * @Description: 单个删除批量删除二合一
     * @Param: [ids]
     * @Return: com.crud.bean.Msg
     * @Author: Alex McAvoy
     * @Date: 2022/4/22 20:05
     **/
    @ResponseBody
    @RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids")String ids){
        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }


    /**
     * @Description: 员工更新方法
     * 如果直接发送ajax=PUT形式的请求，请求体中有数据，但是Employee对象封装不上
     * 原因：
     * Tomcat：
     * 		1、将请求体中的数据，封装一个map。
     * 		2、request.getParameter("empName")就会从这个map中取值。
     * 		3、SpringMVC封装POJO对象的时候，会把POJO中每个属性的值，request.getParamter("email");
     * AJAX发送PUT请求，导致请求体中的数据，request.getParameter("empName")拿不到
     * Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
     * 解决方案：要能支持直接发送PUT之类的请求还要封装请求体中的数据
     * 1、配置上HttpPutFormContentFilter，将请求体中的数据解析包装成一个map
     * 2、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
     * @Param: [employee, request]
     * @Return: com.crud.bean.Msg
     * @Author: Alex McAvoy
     * @Date: 2022/4/22 14:26
     **/
    @ResponseBody
    @RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
    public Msg saveEmp(Employee employee, HttpServletRequest request){
        System.out.println("请求体中的值："+request.getParameter("gender"));
        System.out.println("将要更新的员工数据："+employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * @Description: 根据id查询员工
     * @Param: [id]
     * @Return: com.crud.bean.Msg
     * @Author: Alex McAvoy
     * @Date: 2022/4/22 14:09
     **/
    @RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }


    /**
     * @Description: 检查用户名是否可用
     * @Param: [empName]
     * @Return: com.crud.bean.Msg
     * @Author: Alex McAvoy
     * @Date: 2022/4/21 19:06
     **/
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(@RequestParam("empName")String empName){
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})"; //判断用户名是否是合法的表达式
        if(!empName.matches(regx)){
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }

        //数据库用户名重复校验
        boolean b = employeeService.checkUser(empName);
        if(b){
            return Msg.success();
        }else{
            return Msg.fail().add("va_msg", "用户名不可用");
        }
    }


    /**
     * @Description: 员工保存
     * @Param: [employee, result]
     * @Return: com.crud.bean.Msg
     * @Author: Alex McAvoy
     * @Date: 2022/4/21 18:41
     **/
    @RequestMapping(value="/emp",method= RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            Map<String, Object> map = new HashMap<>(); //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }

    /**
     * @Description: Json 分页查询
     * @Param: [pn]
     * @Return: Msg
     * @Author: Alex McAvoy
     * @Date: 2022/4/20 23:05
     **/
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5); //分页查询
        List<Employee> emps = employeeService.getAll();
        for (int i = 0; i < emps.size(); i++) { //设置部门字段
            Employee employee = emps.get(i);
            int departmentId = employee.getdId();
            String departmentName = departmentService.getName(departmentId).getDeptName();
            emps.get(i).setDepartment(new Department(departmentId, departmentName));
        }

        PageInfo page = new PageInfo(emps, 5);

        return Msg.success().add("pageInfo", page);
    }

    /**
     * @Description: PageHelper 分页查询
     * @Param: [pn, model]
     * @Return: java.lang.String
     * @Author: Alex McAvoy
     * @Date: 2022/4/20 20:12
     **/
//    @RequestMapping("/emps")
    public String getEmps(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 5); //分页查询
        List<Employee> emps = employeeService.getAll();
        for (int i = 0; i < emps.size(); i++) { //设置部门字段
            Employee employee = emps.get(i);
            int departmentId = employee.getdId();
            String departmentName = departmentService.getName(departmentId).getDeptName();
            emps.get(i).setDepartment(new Department(departmentId, departmentName));
        }

        PageInfo page = new PageInfo(emps, 5);
        model.addAttribute("pageInfo", page);

        return "list";
    }

}
