package com.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.bean.base.Employee;
import com.mybatis.bean.page.Student;
import com.mybatis.dao.base.EmployeeMapper;
import com.mybatis.dao.page.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/12 1:56
 * @Version: 1.0
 **/
public class PageTest {
    SqlSessionFactory sqlSessionFactory = null;

    {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * @Description: 测试mybatis能否正常使用
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/12 2:04
     **/
    @Test
    public void testDaoMapper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.getStudentById(1);
        System.out.println(student);

        sqlSession.close();
    }

    @Test
    public void testPage() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        int pageNum = 2; //要查的页数
        int pageSize = 2; //每页几条
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);

        List<Student> students = studentMapper.getStudents();

        for (Student student : students)
            System.out.println(student);
        System.out.println("当前页码:" + page.getPageNum());
        System.out.println("总记录数:" + page.getTotal());
        System.out.println("每页记录数:" + page.getPageSize());
        System.out.println("总页码:" + page.getPages());

        System.out.println();

        int navigateNum = 3; //连续显示多少页
        PageInfo<Student> info = new PageInfo<>(students, navigateNum);
        System.out.println("是否是第一页:" + info.isIsFirstPage());

        System.out.print("连续显示的页码:");
        int[] nums = info.getNavigatepageNums();
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println();

        sqlSession.close();
    }
}
