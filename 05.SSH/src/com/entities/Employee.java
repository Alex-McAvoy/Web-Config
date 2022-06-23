package com.entities;

import java.util.Date;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/27 21:45
 * @Version: 1.0
 **/
public class Employee {
    private Integer id;
    private String name; //不能被修改
    private String email; //不能被修改
    private Date birth;
    private Date createTime;; //不能被修改
    private Department department; //单向 n-1 的关联关系

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", createTime=" + createTime +
                ", department=" + department +
                '}';
    }
}
