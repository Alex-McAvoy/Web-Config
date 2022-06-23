package com.service;

import com.dao.DepartmentDao;
import com.entities.Department;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/28 2:54
 * @Version: 1.0
 **/
public class DepartmentService {

    //显示录入界面
    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }


}
