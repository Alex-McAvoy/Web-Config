package com.jpa.testpersist;

import com.jpa.entity.manytoone.Order;
import com.jpa.entity.basic.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @Description: 单向多对一关系
 * @Author: Alex McAvoy
 * @Date: 2022/3/11 10:46
 * @Version: 1.0
 **/
public class TestManyToOne {
    String persistenceUnitName = "TestPersistenceUnit";
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    EntityTransaction transaction = null;

    @Before
    public void init() {
        //1.创建EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        //2.创建EntityManager
        entityManager = entityManagerFactory.createEntityManager();
        //3.开启事务
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void destroy() {
        //5.提交事务
        transaction.commit();
        //6.关闭EntityManager
        entityManager.close();
        //7.关闭EntityManagerFactory
        entityManagerFactory.close();
    }

    /**
     * @Description: 运行后生成相应orders数据表
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/11 10:35
     **/
    @Test
    public void testManyToOne() {
    }

    /**
     * @Description: 持久化多对一关系时，建议先保存1的一端，再保存n的一端
     *               这样不会多出额外的 update 语句
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/11 10:40
     **/
    @Test
    public void testManyToOnePersist() {
        User user = new User();
        user.setName("BB");
        user.setPassword("bb");
        user.setBirth(new Date());
        user.setCreateTime(new Date());

        Order order1 = new Order();
        order1.setOrderName("BB-bb-1");
        Order order2 = new Order();
        order2.setOrderName("BB-bb-2");

        //设置关联关系
        order1.setUser(user);
        order2.setUser(user);

        //执行持久化操作
        entityManager.persist(user);
        entityManager.persist(order1);
        entityManager.persist(order2);
    }

    /**
     * @Description: 多对一关系的查询，默认采用左外连接的方式获取n一端的对象和其关联的1一端的对象
     *               可在相应实体类中使用 @ManyToOne 的 fetch 属性修改默认关联属性的加载策略
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/11 10:42
     **/
    @Test
    public void testManyToOneFind() {
        Order order = entityManager.find(Order.class,1);
        System.out.println(order.getOrderName());
        System.out.println(order.getUser().getId());
    }

    /**
     * @Description: 多对一关系的删除，由于外键的存在，不能直接删除1的一端
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/11 10:48
     **/
    @Test
    public void testManyToOneRemove() {
        Order order = entityManager.find(Order.class,2);
        entityManager.remove(order);
    }

    /**
     * @Description: 多对一关系的修改
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/11 10:50
     **/
    @Test
    public void testManyToOneUpdate() {
        Order order = entityManager.find(Order.class,1);
        order.getUser().setName("XX");
    }

}
