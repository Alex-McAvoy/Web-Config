package com.mybatis.test;

import com.mybatis.bean.base.Employee;
import com.mybatis.bean.secondcache.Man;
import com.mybatis.bean.secondcache.Woman;
import com.mybatis.dao.secondcache.ManMapper;
import com.mybatis.dao.secondcache.WomanMapper;
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
 * @Date: 2022/4/10 15:45
 * @Version: 1.0
 **/
public class SecondCacheTest {
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

    /** 每个sqlSession都有一个对应的一级缓存
     * 每个sqlSession中的clearCache()方法，会清空当前session的一级缓存
     * **/

    /**
     * @Description: 测试select标签使用二级缓存
     *
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/10 16:15
     **/
    @Test
    public void testSelectCache(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        ManMapper manMapper1 = sqlSession1.getMapper(ManMapper.class);
        ManMapper manMapper2 = sqlSession2.getMapper(ManMapper.class);

        Man man1 = manMapper1.getManById(1);
        System.out.println(man1);
        sqlSession1.close();

        System.out.println();

        Man man2 = manMapper2.getManById(1);
        System.out.println(man2);
        sqlSession2.close();
    }
    
    /**
     * @Description: 测试insert标签使用二级缓存
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/4/10 16:16
     **/
    @Test
    public void testInsertCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        ManMapper manMapper1 = sqlSession1.getMapper(ManMapper.class);
        ManMapper manMapper2 = sqlSession2.getMapper(ManMapper.class);

        Man man1 = manMapper1.getManById(1);
        System.out.println(man1);
        sqlSession1.close();

        System.out.println();

        manMapper2.addMan(new Man(null,"DD","1","DD@163.com"));
        Man man2 = manMapper2.getManById(1);
        System.out.println(man2);
        sqlSession2.close();
    }

    @Test
    public void testEhcache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        WomanMapper womanMapper1 = sqlSession1.getMapper(WomanMapper.class);
        WomanMapper womanMapper2 = sqlSession2.getMapper(WomanMapper.class);

        Woman woman1 = womanMapper1.getWomanById(1);
        System.out.println(woman1);
        sqlSession1.close();

        System.out.println();

        Woman woman2 = womanMapper2.getWomanById(1);
        System.out.println(woman2);
        sqlSession2.close();
    }
}
