package com.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/2/25 2:10
 * @Version: 1.0
 **/
public class AopTest {

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/aopContext.xml");
        Calculator calculator = (Calculator) ctx.getBean(Calculator.class);

        int res = calculator.add(3, 3);
        System.out.println("result:" + res);
        System.out.println();
    }

    //测试异常通知
    @Test
    public void testException() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/config/aopContext.xml");
        Calculator calculator = (Calculator) ctx.getBean(Calculator.class);

        int res=calculator.div(5,0);
        System.out.println("result:" + res);
    }
}
