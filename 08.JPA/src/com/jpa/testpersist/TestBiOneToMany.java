package com.jpa.testpersist;

import com.jpa.entity.bionetomany.Department;
import com.jpa.entity.bionetomany.Student;
import com.jpa.entity.onetomany.Car;
import com.jpa.entity.onetomany.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Description: 双向一对多关系
 * @Author: Alex McAvoy
 * @Date: 2022/3/11 13:57
 * @Version: 1.0
 **/
public class TestBiOneToMany {
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
     * @Description: 运行后生成相应students和departments数据表
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/11 10:35
     **/
    @Test
    public void testBiOneToMany() {
    }

    /**
     * @Description: 双向一对多关系保存
     *               执行保存时，若先保存 n 的一端，默认情况下会多出 n 条 update 语句
     *               若先保存 1 的一端，默认情况下会多出 n 条 update 语句
     *               因此，在进行双向一对多关系时，及建议使用 n 的一端来维护关系，1 的一端不维护，可以有效减少 SQL 语句
     *               可以使用 @OneToMany 注解中的 mappedBy 属性来放弃维护，同时，取消掉 @JoinColumn 注解
     * @Param: []
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/3/11 11:09
     **/
    @Test
    public void testOneToManyPersist() {
        Student student = new Student();
        student.setName("CC");

        Department department1 = new Department();
        department1.setDepartmentName("cc");
        Department department2 = new Department();
        department2.setDepartmentName("dd");

        //建立关联关系
        student.getDepartments().add(department1);
        student.getDepartments().add(department2);

        department1.setStudent(student);
        department2.setStudent(student);

        //执行持久化操作
        entityManager.persist(student);
        entityManager.persist(department1);
        entityManager.persist(department2);
    }

}
