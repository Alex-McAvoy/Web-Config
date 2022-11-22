package com.dao;

import com.entities.Department;
import com.entities.Employee;

import java.util.List;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/28 2:51
 * @version 1.0
 **/
public class DepartmentDao extends BaseDao {
    //显示录入界面
    public List<Department> getAll(){
        String hql = "FROM Department";
        return getSession().createQuery(hql).list();
    }
}
