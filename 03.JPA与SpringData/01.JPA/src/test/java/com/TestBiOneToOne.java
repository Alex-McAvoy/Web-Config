package com;

import com.entity.bionetoone.Work;
import com.entity.bionetoone.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *  双向一对一关联关系
 * @author Alex McAvoy
 * @date 2022/3/11 14:28
 * @version 1.0
 **/
public class TestBiOneToOne {
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
     *  运行后生成相应 works 和 workers数据表
     * @author Alex McAvoy
     * @date 2022/3/11 10:35
     **/
    @Test
    public void testBiOneToOne() {
    }

    /**
     *  双向一对一关联关系保存
     *  建议先保存不维护关联关系即没有外键的一方，这样不会多出 update 语句
     * @author Alex McAvoy
     * @date 2022/3/11 14:33
     **/
    @Test
    public void testBiOneToOnePersist() {
        Worker worker = new Worker();
        worker.setWorkerName("AA");

        Work work = new Work();
        work.setWorkName("aa");

        //设置关联关系
        work.setWorker(worker);
        worker.setWork(work);

        //执行持久化操作
        entityManager.persist(worker);
        entityManager.persist(work);

    }

    /**
     *  双向一对一关联关系查询
     *  1.testBiOneToOneFind1()
     *   默认情况下，若获取维护关联关系的一方，则会通过左外连接的方式获取其关联的对象
     *   但可以通过 @OneToOne 的 fetch 属性设置懒加载的方式来直接获取
     *  2.testBiOneToOneFind2()
     *   默认情况下，若获取不维护关联关系的一方，则会通过左外连接的方式获取其关联的对象
     *   可以通过 @OneToOne 的 fetch 属性设置懒加载方式，但依然会再发送 SQL 来初始化其关联的对象
     *   说明在不维护关联关系的一方，不建议修改 fetch 属性
     * @author Alex McAvoy
     * @date 2022/3/11 14:36
     **/
    @Test
    public void testBiOneToOneFind1() {
        Work work = entityManager.find(Work.class,1);
        System.out.println(work.getWorkName());
        System.out.println(work.getWorker().getClass().getName());
    }

    @Test
    public void testBiOneToOneFind2() {
        Worker worker = entityManager.find(Worker.class,1);
        System.out.println(worker.getWorkerName());
        System.out.println(worker.getClass().getName());
    }
}
