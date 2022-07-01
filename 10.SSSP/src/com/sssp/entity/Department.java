package com.sssp.entity;

import javax.persistence.*;

/**
 * @Description: 实体类
 * @Author: Alex McAvoy
 * @Date: 2022/3/15 11:29
 * @Version: 1.0
 **/
@Cacheable
@Table(name = "department")
@Entity
public class Department {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "department_name")
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
