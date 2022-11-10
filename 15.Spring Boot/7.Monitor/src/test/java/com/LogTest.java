package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 日志测试类
 * @Author: Alex McAvoy
 * @Date: 2022/9/16 21:58
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {
    // 日志记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoad() {
        /** 日志级别
         * 由低到高：trace<debug<info<warn<error
         * 实际开发中可调整输出的日志级别，即日志只会在选中的级别及以后的高级别输出
         * 默认为info级别
         * */
        logger.trace("trace日志");
        logger.debug("debug日志");
        logger.info("info日志");
        logger.warn("warn日志");
        logger.error("error日志");

    }
}
