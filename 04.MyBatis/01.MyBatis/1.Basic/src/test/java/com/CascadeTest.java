package com;

import com.bean.cascade.Department;
import com.bean.cascade.Worker;
import com.dao.cascade.DepartmentMapper;
import com.dao.cascade.WorkerMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


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
     * 测试ResultMap
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
     * 测试级联查询
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
     * 测试多表联合查询
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
     * 测试鉴别器discriminator
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
