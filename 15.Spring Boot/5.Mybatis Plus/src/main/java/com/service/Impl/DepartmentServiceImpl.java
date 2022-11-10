package com.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bean.Department;
import com.dao.DepartmentDao;
import com.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Department 的 Service 实现类
 * @author Alex McAvoy
 * @date 2022/9/29 21:09
 * @version 1.0
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired(required = false)
    DepartmentDao departmentDao;

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentDao.selectById(id);
    }

    @Override
    public List<Department> getDepartmentList() {
        QueryWrapper<Department> departmentQueryWrapper = new QueryWrapper<>();
        return departmentDao.selectList(departmentQueryWrapper);
    }

}
