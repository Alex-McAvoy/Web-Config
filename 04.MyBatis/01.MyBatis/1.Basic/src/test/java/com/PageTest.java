package com;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bean.page.Student;
import com.dao.page.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
     * 测试mybatis能否正常使用
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
