package com.mybatis.test;

import com.mybatis.bean.cascade.Department;
import com.mybatis.bean.cascade.Worker;
import com.mybatis.dao.cascade.DepartmentMapper;
import com.mybatis.dao.cascade.WorkerMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/3 15:03
 * @Version: 1.0
 **/
public class CascadeTest {

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
     * @Description: 测试ResultMap
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/3 15:33
     **/
    @Test
    public void testResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WorkerMapper workerMapper = sqlSession.getMapper(WorkerMapper.class);

        Worker worker = workerMapper.getWorkerById(1);
        System.out.println(worker);

        sqlSession.close();
    }

    /**
     * @Description: 测试级联查询
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/3 16:02
     **/
    @Test
    public void testCascadeQuery () {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WorkerMapper workerMapper = sqlSession.getMapper(WorkerMapper.class);

        //级联查询方式1
        Worker worker = workerMapper.getWorkerAndDepartmentById1(1);
        System.out.println(worker);
        //级联查询方式2
        worker = workerMapper.getWorkerAndDepartmentById2(1);
        System.out.println(worker);
        //级联查询方式3
        worker = workerMapper.getWorkerAndDepartmentById3(1);
        System.out.println(worker);

        sqlSession.close();
    }

    /**
     * @Description: 测试多表联合查询
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/3 19:35
     **/
    @Test
    public void testUnionQuery() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

        //多表联查方式1
        Department department = departmentMapper.getDepartmentByUnionQuery1(1);
        System.out.println(department);

        //多表联查方式2
        department = departmentMapper.getDepartmentByUnionQuery2(1);
        System.out.println(department);

        sqlSession.close();
    }

    /**
     * @Description: 测试鉴别器discriminator
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/3 20:48
     **/
    @Test
    public void testDiscriminator() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WorkerMapper workerMapper = sqlSession.getMapper(WorkerMapper.class);

        Worker worker = workerMapper.getWorkerByDepartmentIdForTestDiscriminator(1);
        System.out.println(worker);
        worker = workerMapper.getWorkerByDepartmentIdForTestDiscriminator(2);
        System.out.println(worker);

        sqlSession.close();
    }
}
