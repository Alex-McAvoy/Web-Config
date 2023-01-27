package com.dao;

import com.bean.Department;
import com.bean.DepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 部门 Dao
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/4/21 20:30
 **/
public interface DepartmentMapper {
    int countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer deptId);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}