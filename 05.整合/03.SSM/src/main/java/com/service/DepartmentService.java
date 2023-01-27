package com.service;

import com.bean.Department;
import com.dao.DepartmentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门 Service
 * @author Alex McAvoy
 * @date 2022/4/20 22:27
 * @version 1.0
 **/
@Service
public class DepartmentService {
    @Autowired(required = false)
    DepartmentMapper departmentMapper;

    public Department getName(Integer id){
        return departmentMapper.selectByPrimaryKey(id);
    }

    public List<Department> getDepts() {
        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }
}
