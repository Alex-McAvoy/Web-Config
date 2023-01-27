package com;

import com.entity.basic.User;
import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 一对一映射
 * @author Alex McAvoy
 * @date 2022/3/11 10:46
 * @version 1.0
 **/
public class TestPersistence {
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

    /**
     * 类似于 Hibernate 中的 Save 方法，使对象由临时状态变为持久化状态
     * 但与 save 方法不同是，若对象有 id 则不能执行 insert 操作，会抛出异常
     * @author Alex McAvoy
     * @date 2022/3/10 18:24
     **/
    @Test
    public void testPersistence() {
        //4.进行持久化操作
        User user = new User();
        user.setName("123");
        user.setPassword("123");
        user.setBirth(new Date());
        user.setCreateTime(new Date());
        entityManager.persist(user);
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
     * 根据 id 查找数据表的对象，相当于 Hibernate 中 Session 的 get 方法
     * 调用完毕后即向数据库发 SQL 初始化对象
     * @author Alex McAvoy
     * @date 2022/3/10 14:23
     **/
    @Test
    public void testFind() {
        User user = entityManager.find(User.class, 5);
        System.out.println("-----------------");
        System.out.println(user);
    }

    /**
     * 根据 id 查找数据表的对象，相当于 Hibernate 中 Session 的 load 方法
     * 直到真正使用对象时，才向数据库发 SQL 初始化对象
     * @author Alex McAvoy
     * @date 2022/3/10 14:25
     **/
    @Test
    public void testGetReference() {
        User user = entityManager.getReference(User.class, 5);
        System.out.println("-----------------");
        System.out.println(user);
    }

    /**
     * 类似于 Hibernate 的 delete 方法，但该方法只能移除持久化对象，
     * 而 delete 方法还可以移除游离对象
     * @author Alex McAvoy
     * @date 2022/3/10 18:26
     **/
    @Test
    public void testRemove() {
//        //游离对象
//        User user = new User();
//        user.setId(2);
//        session.delete(user);

        User user = entityManager.find(User.class, 2);
        entityManager.remove(user);
    }

    /**
     * 类似 Hibernate 的 saveOrUpdate 方法
     *               1.testMerge1(): 若传入的是临时对象，会创建一个新的对象，并将临时对象的属性复制到新对象中，
     *                 然后对新的对象进行持久化操作
     *               2.若传入的是游离对象，即传入的对有 OID，有以下三种情况：
     *                 1）testMerge2():
     *                   1.1 在 EntityManager 缓存中没有该对象
     *                   1.2 若在数据库中也没有对应记录
     *                   1.3 JPA 会创建一个新对象，然后将当前游离对象属性复刻到新创建的对象中
     *                   1.4 对新创建的对象执行 insert 操作
     *                 2）testMerge3():
     *                   2.1 在 EntityManager 缓存中没有该对象
     *                   2.2 若在数据库中有对应记录
     *                   2.3 JPA 会查询对应的记录，然后返回该记录对应的对象
     *                   2.4 将游离对象的属性复制到查询到的对象中
     *                   2.5 对查询到的对象执行 update 操作
     *                 3）testMerge4():
     *                   3.1 若 EntityManager 缓存中有该对象
     *                   3.2 JPA 会把游离对象的属性复制到 EntityManager 缓存中的对象
     *                   3.3 对 EntityManager 缓存中的对象执行 update 操作
     * @author Alex McAvoy
     * @date 2022/3/10 18:34
     **/
    @Test
    public void testMerge1() {
        User user1 = new User();
        user1.setName("123");
        user1.setPassword("123");
        user1.setBirth(new Date());
        user1.setCreateTime(new Date());

        User user2 = entityManager.merge(user1);
        System.out.println(user1.getId());
        System.out.println(user2.getId());
    }

    @Test
    public void testMerge2() {
        User user1 = new User();
        user1.setName("123");
        user1.setPassword("123");
        user1.setBirth(new Date());
        user1.setCreateTime(new Date());

        user1.setId(100);

        User user2 = entityManager.merge(user1);
        System.out.println(user1.getId());
        System.out.println(user2.getId());
    }

    @Test
    public void testMerge3() {
        User user1 = new User();
        user1.setName("456");
        user1.setPassword("456");
        user1.setBirth(new Date());
        user1.setCreateTime(new Date());

        user1.setId(4);

        User user2 = entityManager.merge(user1);
        System.out.println(user1.getId());
        System.out.println(user2.getId());
    }

    @Test
    public void testMerge4() {
        User user1 = new User();
        user1.setName("789");
        user1.setPassword("789");
        user1.setBirth(new Date());
        user1.setCreateTime(new Date());

        user1.setId(5);

        User user2 = entityManager.find(User.class,5);
        entityManager.merge(user1);

        System.out.println(user1 == user2); //false
    }

    /**
     * 同 Hibernate 中的 flush 方法
     * @author Alex McAvoy
     * @date 2022/3/10 18:53
     **/
    @Test
    public void testFlush() {
        User user = entityManager.find(User.class,7);
        System.out.println(user);

        user.setName("AA");
        entityManager.flush();
    }

    /**
     * 同 Hibernate 中的 refresh 方法
     * @author Alex McAvoy
     * @date 2022/3/10 18:54
     **/
    @Test
    public void testRefresh() {
        User user = entityManager.find(User.class,7);
        user = entityManager.find(User.class,7);
        
        entityManager.refresh(user);
    }

    /**
     * 测试二级缓存
     * @author Alex McAvoy
     * @date 2022/3/11 19:02
     **/
    @Test
    public void testSecondLevelCache() {
        String jpql = "FROM User u";
        Query query = entityManager.createQuery(jpql);
        List<User> users = query.setHint(QueryHints.HINT_CACHEABLE,true).getResultList();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        users = query.setHint(QueryHints.HINT_CACHEABLE,true).getResultList();
    }

    /**
     * JPQL 测试
     * @author Alex McAvoy
     * @date 2022/3/11 19:10
     **/
    @Test
    public void testJPQL() {
        String jpql = "FROM User u WHERE u.id > ?";
        Query query = entityManager.createQuery(jpql);

        //占位符索引从1开始
        query.setParameter(1,4);
        List<User> users = query.getResultList();
        System.out.println(users.size());
    }
}
