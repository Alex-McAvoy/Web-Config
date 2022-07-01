package com.mybatis.dao.page;

import com.mybatis.bean.page.Student;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/12 1:56
 * @Version: 1.0
 **/
public interface StudentMapper {
    public Student getStudentById(Integer id);

    public List<Student> getStudents();
}
