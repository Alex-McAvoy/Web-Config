package com.mybatis.dao.cascade;

import com.mybatis.bean.cascade.Worker;

import java.util.List;


/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/3 15:02
 * @Version: 1.0
 **/
public interface WorkerMapper {
    public Worker getWorkerById(Integer id);

    public Worker getWorkerAndDepartmentById1(Integer id);
    public Worker getWorkerAndDepartmentById2(Integer id);
    public Worker getWorkerAndDepartmentById3(Integer id);

    public List<Worker> getWorkerByDepartmentId(Integer departmentId);

    public Worker getWorkerByDepartmentIdForTestDiscriminator(Integer id);
}
