package com;

import com.entity.manytomany.Category;
import com.entity.manytomany.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *  双向多对多关系
 * @author Alex McAvoy
 * @date 2022/3/11 18:32
 * @version 1.0
 **/
public class TestManyToMany {
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
     *  运行后生成相应 items、categories、item-category 数据表
     * @author Alex McAvoy
     * @date 2022/3/11 10:35
     **/
    @Test
    public void testManyToMany() {
    }

    /**
     *  多对多关系持久化操作
     * @author Alex McAvoy
     * @date 2022/3/11 18:46
     **/
    @Test
    public void testManyToManyPersistence() {
        Item item1 = new Item();
        item1.setItemName("i-1");
        Item item2 = new Item();
        item2.setItemName("i-2");

        Category category1 = new Category();
        category1.setCategoryName("c-1");
        Category category2 = new Category();
        category2.setCategoryName("c-2");

        //设置关联关系
        item1.getCategories().add(category1);
        item1.getCategories().add(category2);
        item2.getCategories().add(category1);
        item2.getCategories().add(category2);

        category1.getItems().add(item1);
        category1.getItems().add(item2);
        category2.getItems().add(item1);
        category2.getItems().add(item2);

        //持久化操作
        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.persist(category1);
        entityManager.persist(category2);
    }

    /**
     *  多对多关系查询操作，对于关联的集合对象，默认使用懒加载策略
     *  使用维护关联关系的一方获取，与使用不维护关联关系的一方获取，SQL 语句相同
     * @author Alex McAvoy
     * @date 2022/3/11 18:48
     **/
    @Test
    public void testManyToManyFind() {
        Item item = entityManager.find(Item.class,1);
        System.out.println(item.getItemName());
        System.out.println(item.getCategories().size());

        Category category = entityManager.find(Category.class,1);
        System.out.println(category.getCategoryName());
        System.out.println(category.getItems().size());
    }
}
