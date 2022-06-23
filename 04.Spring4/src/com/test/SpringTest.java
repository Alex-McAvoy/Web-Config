package com.test;

import com.beans.Hello;
import com.beans.Person;
import com.beans.Car;
import com.beans.Worker;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/23 0:00
 * @Version: 1.0
 **/
public class SpringTest {

    @Test
    public void TestConstruction() {
        /*
         * 1.创建 Spring 的 IOC 容器对象
         * 2.从 IOC 容器中获取 Bean 实例
         * 3.调用 Bean 方法
         * */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/applicationContext.xml");


        //按顺序构造器注入
        Hello hello = (Hello) ctx.getBean("hello");
//        Hello hello = (Hello) ctx.getBean(Hello.class); //利用类型返回 IOC 容器的 Bean,要求容器中只有一个该类型的Bean
        hello.hello();

        //重载构造器注入
        Car car = (Car) ctx.getBean("car");
        System.out.println(car);
    }

    @Test
    public void TestParam() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/applicationContext.xml");

        //属性注入
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);

        //内部 Bean 属性注入
        Person person1 = (Person) ctx.getBean("person1");
        System.out.println(person1);
    }

    @Test
    public void TestCollection() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
        // 集合 Bean
        Worker worker = (Worker) ctx.getBean("worker");
        System.out.println(worker);

        Worker worker1 = (Worker) ctx.getBean("worker1");
        System.out.println(worker1);
    }
}
