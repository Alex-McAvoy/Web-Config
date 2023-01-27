package com.mapper.mappers;

import com.mapper.beans.Employee;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Description: Employee 对应的 Mapper，具体操作数据库的 Mapper 接口，需要继承通用 Mapper 所提供的接口
 * @Author: Alex McAvoy
 * @Date: 2022/6/24 20:02
 * @Version: 1.0
 **/
public interface EmployeeMapper extends Mapper<Employee> {

}
