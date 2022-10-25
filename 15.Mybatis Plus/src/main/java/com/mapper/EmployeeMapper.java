package com.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bean.Employee;

/** Mapper 接口
 * 基于 Mybatis：
 *  - 需要编写 EmployeeMapper 接口，并手动编写 CRUD 方法
 *  - 提供 EmployeeMapper.xml 映射文件，并手动编写每个方法对应的 SQL 语句
 * 基于 MP
 *  - 只需要创建 EmployeeMapper 接口, 并继承 BaseMapper 接口（需要指定当前Mapper接口所操作的类）
 *  - 需要完成的所有操作，甚至不需要创建 SQL 映射文件
 * **/
public interface EmployeeMapper extends BaseMapper<Employee> {
}
