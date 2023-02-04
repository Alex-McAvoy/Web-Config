package com.entity.bionetomany;

import javax.persistence.*;
import com.entity.bionetomany.Student;

/**
 *  双向一对多关联关系实体类
 * @author Alex McAvoy
 * @date 2022/3/11 13:51
 * @version 1.0
 **/
@Table(name = "departments")
@Entity
public class Department {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "department_name")
    private String departmentName;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
