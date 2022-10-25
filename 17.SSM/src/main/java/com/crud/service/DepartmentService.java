package com.crud.service;

import com.crud.bean.Department;
import com.crud.dao.DepartmentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/20 22:27
 * @Version: 1.0
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
