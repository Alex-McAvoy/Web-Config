package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2019/2/19 3:28
 * @Version: 1.0
 **/
public class HibernateTest {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    @Before
    public void init(){
        try {
            //1. 创建 SessionFactory 对象
            //Configuration: 对应 Hibernate 的基本配置信息和对象关系映射信息
            Configuration configuration = new Configuration().configure();
            //ServiceRegistry: Hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
            ServiceRegistry serviceRegistry =
                    new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                            .buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            //2. 创建 Session 对象
            session = sessionFactory.openSession();

            //3. 开启事务
            transaction = session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        System.out.println("测试开始...");

        try {
            //5. 执行 get 操作
            TestUsersEntity user = (TestUsersEntity) session.get(TestUsersEntity.class, 1);
            System.out.println(user);
            System.out.println("输出测试");

            //6. 提交事务
            transaction.commit();
        } catch (Exception e) {
            //7. 事务回滚
            if(transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        System.out.println("测试结束...");
    }

    @After
    public void destroy(){
        //8. 关闭 Session
        session.close();
        //9. 关闭 SessionFactory 对象
        sessionFactory.close();
    }
}
