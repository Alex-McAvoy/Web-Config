package com;

import com.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 注入 Bean 测试类
 * @author Alex McAvoy
 * @date 2022/9/16 21:58
 * @version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    User user;

    @Test
    public void contextLoads() {
        System.out.println(user);
    }
}
