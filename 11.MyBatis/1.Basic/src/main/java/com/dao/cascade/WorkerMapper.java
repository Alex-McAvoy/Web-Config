package com.dao.cascade;

import com.bean.cascade.Worker;

import java.util.List;


public interface WorkerMapper {
    public Worker getWorkerById(Integer id);

    public Worker getWorkerAndDepartmentById1(Integer id);
    public Worker getWorkerAndDepartmentById2(Integer id);
    public Worker getWorkerAndDepartmentById3(Integer id);

    public List<Worker> getWorkerByDepartmentId(Integer departmentId);

    public Worker getWorkerByDepartmentIdForTestDiscriminator(Integer id);
}
