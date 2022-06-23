package com.entities;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/27 21:45
 * @Version: 1.0
 **/
public class Department {
    private Integer id;
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
}
