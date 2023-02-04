package com;

import com.entity.Department;
import com.repository.DepartmentRepository;
import org.hibernate.ejb.QueryHints;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 *  测试类
 * @author Alex McAvoy
 * @date 2022/3/15 11:13
 * @version 1.0
 **/
public class SSSPTest {
    private ApplicationContext ctx = null;
    private DepartmentRepository departmentRepository = null;
    private EntityManagerFactory entityManagerFactory = null;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        departmentRepository = ctx.getBean(DepartmentRepository.class);
        entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
    }

    /**
     *  测试数据源
     * @author Alex McAvoy
     * @date 2022/3/15 11:37
     **/
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    /**
     *  JPA 生成数据表
     * @author Alex McAvoy
     * @date 2022/3/15 15:06
     **/
    @Test
    public void testGenerateTable() {
    }

    /**
     *  测试 JPA 的二级缓存
     * @author Alex McAvoy
     * @date 2022/3/15 18:11
     **/
    @Test
    public void testJPASecondLevelCache() {
        String jpql = "FROM Department d";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(jpql);
        List<Department> departments = query.setHint(QueryHints.HINT_CACHEABLE,true).getResultList();
        entityManager.close();
        
        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        departments = query.setHint(QueryHints.HINT_CACHEABLE,true).getResultList();
        entityManager.close();
        
    }

    /**
     *  测试二级缓存
     * @author Alex McAvoy
     * @date 2022/3/15 18:09
     **/
    @Test
    public void testRepositorySecondLevelCache() {
        List<Department> departments = departmentRepository.getAll();
        departments = departmentRepository.getAll();
    }
}
