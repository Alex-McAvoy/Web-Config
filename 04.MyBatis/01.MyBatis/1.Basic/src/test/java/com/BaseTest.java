package com;

import com.bean.base.Employee;
import com.dao.base.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseTest {

    //sqlSessionFactory 是非线程安全的，因此在实际开发中，每次使用sqlSessionFactory都应该获取新的对象
    SqlSessionFactory sqlSessionFactory = null;

    //根据全局配置文件 mybatis-config.xml 创建 SqlSessionFactory 对象
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

    /** 不使用 dao 接口情况下的 sql 语句调用（目前该方式已弃用）
     **/
    @Test
    public void testSql() {
        //获取 sqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行对应 SQL 语句
        /** openSession.selectOne(statement, parameter)
         *  statement: sql 的唯一标识，即 xxxMapper.xml 中 sql 语句的 id
         *  parameter: 执行 sql 要使用的参数，即映射 xml 对应 sql 的 #{} 中的值
         * **/
        Employee employee = sqlSession.selectOne("com.dao.base.EmployeeMapper.getEmployeeById", 1);
        System.out.println(employee);

        //关闭 openSession 对象
        sqlSession.close();
    }

    /**
     * 使用 dao 接口后的 sql 语句调用（目前常用该方式）
     * MyBatis 会为接口自动创建代理对象，实现动态绑定，代理对象去执行 CRUD
     **/
    @Test
    public void testDaoMapper() {
        //获取 sqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取 dao 接口的实现类对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
        //关闭 openSession 对象
        sqlSession.close();
    }

    /**
     * 测试添加
     **/
    @Test
    public void testAddEmployee() {
        //sqlSession 不会自动提交数据，设置 .openSession(true) 则可自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = new Employee(null,"alex","1","22@qq.com");
        employeeMapper.addEmployee(employee);
        System.out.println(employee.getId());

        //手动提交数据
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试修改
     **/
    @Test
    public void testUpdateEmployee() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = new Employee(1,"aa","1","AA@163.com");
        employeeMapper.updateEmployee(employee);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试删除
     **/
    @Test
    public void testDeleteEmployeeById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        employeeMapper.deleteEmployeeById(2);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试查询多个参数映射处理
     **/
    @Test
    public void testGetEmployeeByIdAndName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = employeeMapper.getEmployeeByIdAndName(1,"aa");
        System.out.println(employee);

        sqlSession.close();
    }

    /**
     * 测试查询命名参数
     **/
    @Test
    public void testGetEmployeeByIdAndGender() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = employeeMapper.getEmployeeByIdAndGender(1,"1");
        System.out.println(employee);

        sqlSession.close();
    }

    /**
     * 测试查询命名参数(传入Map方式)
     **/
    @Test
    public void testGetEmployeeByMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","aa");
        Employee employee = employeeMapper.getEmployeeByMap(map);
        System.out.println(employee);

        sqlSession.close();
    }

    /**
     * 测试查询返回集合
     **/
    @Test
    public void testGetEmployeeByNameLike() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        List<Employee> employeeList = employeeMapper.getEmployeeByNameLike("%a%");
        System.out.println(employeeList);

        sqlSession.close();
    }

    /**
     * 测试查询返回一条记录的 Map
     **/
    @Test
    public void testGetEmployeeByIdReturnMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        Map<String,Object> map = employeeMapper.getEmployeeByIdReturnMap(1);
        System.out.println(map);

        sqlSession.close();
    }

    /**
     * 测试查询封装多条记录的 Map
     **/
    @Test
    public void testGetEmployeeByNameReturnMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        Map<Integer, Employee> map = employeeMapper.getEmployeeByNameReturnMap("%a%");
        System.out.println(map);

        sqlSession.close();
    }
}
