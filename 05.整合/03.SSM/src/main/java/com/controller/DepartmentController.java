package com.controller;

import com.bean.Department;
import com.bean.Msg;
import com.service.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 部门控制器
 * @author Alex McAvoy
 * @date 2022/4/21 17:33
 * @version 1.0
 **/
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 返回所有的部门信息
     * @return com.bean.Msg
     * @author Alex McAvoy
     * @date 2022/4/21 17:33
     **/
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        //查出的所有部门信息
        List<Department> list = departmentService.getDepts();
        return Msg.success().add("depts", list);
    }
}
