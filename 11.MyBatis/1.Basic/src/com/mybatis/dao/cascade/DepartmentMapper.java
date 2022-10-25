package com.mybatis.dao.cascade;

import com.mybatis.bean.cascade.Department;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/3 18:31
 * @Version: 1.0
 **/
public interface DepartmentMapper {
    public Department getDepartmentById(Integer id);

    public Department getDepartmentByUnionQuery1(Integer id);
    public Department getDepartmentByUnionQuery2(Integer id);
}
