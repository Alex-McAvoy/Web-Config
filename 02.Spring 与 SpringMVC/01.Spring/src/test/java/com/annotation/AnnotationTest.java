package com.annotation;

import com.annotation.contrroller.UserController;
import com.annotation.repository.UserRepository;
import com.annotation.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO
 * @author Alex McAvoy
 * @date 2022/2/25 0:47
 * @version 1.0
 **/
public class AnnotationTest {

    @Test
    public void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/annotation.xml");

        TestObject testObject = (TestObject) ctx.getBean("testObject");
        System.out.println(testObject);

        UserController userController = (UserController) ctx.getBean("userController");
        System.out.println(userController);

        UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
        System.out.println(userRepository);

        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println(userService);
    }

    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/annotation.xml");

        UserController userController = (UserController) ctx.getBean("userController");
        userController.execute();
    }

}
