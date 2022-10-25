package com.crud.controller;

import com.crud.bean.Department;
import com.crud.bean.Msg;
import com.crud.service.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/21 17:33
 * @Version: 1.0
 **/
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * @Description: 返回所有的部门信息
     * @Param: []
     * @Return: Msg
     * @Author: Alex McAvoy
     * @Date: 2022/4/21 17:33
     **/
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list = departmentService.getDepts(); //查出的所有部门信息
        return Msg.success().add("depts", list);
    }
}
