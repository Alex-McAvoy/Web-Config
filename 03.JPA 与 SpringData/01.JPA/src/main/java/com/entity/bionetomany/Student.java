package com.entity.bionetomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  双向一对多关联关系实体类
 * @author Alex McAvoy
 * @date 2022/3/11 13:50
 * @version 1.0
 **/
@Table(name = "students")
@Entity
public class Student {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    //可以使用 @OneToMany 注解中的 mappedBy 属性来选择对方的某个属性，从而放弃维护该端
    //同时，要取消掉 @JoinColumn 注解
//    @JoinColumn(name = "student_id")
    @OneToMany(mappedBy = "student")
    private Set<Department> departments = new HashSet<>();

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

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
