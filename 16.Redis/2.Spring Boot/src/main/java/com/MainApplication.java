package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Boot 整合 MyBatis Plus
 * - 开启基于注解的缓存：@EnableCaching
 * @author Alex McAvoy
 * @date 2022/09/28 22:11
 * @version 1.0
 **/
@EnableCaching
@MapperScan(value = "com.dao")
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
