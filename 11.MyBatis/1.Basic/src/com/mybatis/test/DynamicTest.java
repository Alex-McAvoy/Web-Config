package com.mybatis.test;

import com.mybatis.bean.dynamic.Person;
import com.mybatis.dao.dynamic.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/4/3 19:14
 * @Version: 1.0
 **/
public class DynamicTest {
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
     * @Description: 测试动态SQL的if/where
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/4 16:30
     **/
    @Test
    public void testDynamicIfAndWhere() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(null,"%A%","AA@163.com",null);
        List<Person> persons = personMapper.getPersonByConditionIf(person);
        System.out.println(persons);

        sqlSession.close();
    }

    /**
     * @Description: 测试动态SQL的if/trim
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/4 16:25
     **/
    @Test
    public void testDynamicIfAndTrim() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(null,"%A%","AA@163.com",null);
        List<Person> persons = personMapper.getPersonByConditionTrim(person);
        System.out.println(persons);

        sqlSession.close();
    }

    /**
     * @Description: 测试动态SQL的choose
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/8 18:56
     **/
    @Test
    public void testDynamicChoose() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(null,"%A%","AA@163.com",null);
        List<Person> persons = personMapper.getPersonByConditionTrim(person);
        System.out.println(persons);

        person = new Person(1,"%A%","AA@163.com",null);
        persons = personMapper.getPersonByConditionTrim(person);
        System.out.println(persons);

        sqlSession.close();
    }

    /**
     * @Description: 测试动态SQL的set
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/8 19:06
     **/
    @Test
    public void testDynamicIfSet() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(1,"XX","XX@163.com","1");
        personMapper.updateWorkerBySet(person);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * @Description: 测试动态SQL的for-each
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/8 19:11
     **/
    @Test
    public void testDynamicForeach(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(1,"XX","XX@163.com","1");
        List<Person> persons = personMapper.getPersonByConditionForeach(Arrays.asList(1,2,3));
        System.out.println(persons);

        sqlSession.close();
    }
}
