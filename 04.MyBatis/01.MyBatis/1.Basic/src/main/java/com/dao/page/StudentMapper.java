package com.dao.page;

import com.bean.page.Student;
import java.util.List;


public interface StudentMapper {
    public Student getStudentById(Integer id);

    public List<Student> getStudents();
}
