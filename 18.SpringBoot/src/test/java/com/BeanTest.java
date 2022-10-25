package com;

import com.bean.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 注入Bean测试类
 * @Author: Alex McAvoy
 * @Date: 2022/9/16 21:58
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {
    @Autowired
    TestBean testBean;

    @Test
    public void contextLoads() {
        System.out.println(testBean);
    }
}
