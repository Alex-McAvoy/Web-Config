package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Boot 环境测试
 * - 标注主程序类: @SpringBootApplication 说明是一个 SpringBoot 应用
 * - 除 @SpringBootApplication 外可使用 @ImportResource 注解，使用编写的 Spring 配置文件 beans.xml
 *   但 SpringBoot 不推荐使用，推荐使用配置类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/10 8:56
 **/
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
