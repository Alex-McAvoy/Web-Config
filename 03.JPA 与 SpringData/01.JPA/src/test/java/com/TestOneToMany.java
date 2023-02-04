package com;

import com.entity.onetomany.Car;
import com.entity.onetomany.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 单向一对多关系
 * @author Alex McAvoy
 * @date 2022/3/11 11:02
 * @version 1.0
 **/
public class TestOneToMany {
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
     *  运行后生成相应person和car数据表
     * @author Alex McAvoy
     * @date 2022/3/11 10:35
     **/
    @Test
    public void testOneToMany() {
    }

    /**
     *  一对多关系保存
     * 执行时一定会多出 update 语句，因为n的一端插入时不会同时插入外键
     * @author Alex McAvoy
     * @date 2022/3/11 11:09
     **/
    @Test
    public void testOneToManyPersist() {
        Person person = new Person();
        person.setName("AA");

        Car car1 = new Car();
        car1.setCarName("aa");
        Car car2 = new Car();
        car2.setCarName("bb");

        //建立关联关系
        person.getCars().add(car1);
        person.getCars().add(car2);

        //执行持久化操作
        entityManager.persist(person);
        entityManager.persist(car1);
        entityManager.persist(car2);
    }

    /**
     *  一对多关系查询，默认对关联的多的一方使用懒加载
     *  同样可在相应实体类中使用 @OneToMany 的 fetch 属性更改
     * @author Alex McAvoy
     * @date 2022/3/11 11:10
     **/
    @Test
    public void testOneToManyFind() {
        Person person = entityManager.find(Person.class,1);
        System.out.println(person.getName());
        System.out.println(person.getCars().size());
    }

    /**
     *  一对多关系删除，若删除1的一端，会默认将关联的一端的外键置空
     *  可通过 @OneToMany 的 cascade 属性来修改删除策略，从而进行级联删除
     * @author Alex McAvoy
     * @date 2022/3/11 11:14
     **/
    @Test
    public void testOneToManyRemove() {
        Person person = entityManager.find(Person.class,2);
        entityManager.remove(person);
    }

    /**
     *  一对关系修改
     * @author Alex McAvoy
     * @date 2022/3/11 11:16
     **/
    @Test
    public void testOneToManyUpdate() {
        Person person = entityManager.find(Person.class,1);
        person.getCars().iterator().next().setCarName("JJ");
    }
}
