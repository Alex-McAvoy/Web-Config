package com;

import com.bean.dynamic.Person;
import com.dao.dynamic.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.io.InputStream;

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
     * 测试动态SQL的if/where
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
     * 测试动态SQL的if/trim
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
     * 测试动态SQL的choose
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
     * 测试动态SQL的set
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
     * 测试动态SQL的for-each
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
