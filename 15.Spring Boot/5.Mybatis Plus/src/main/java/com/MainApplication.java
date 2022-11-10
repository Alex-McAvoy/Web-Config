package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot 整合 MyBatis Plus
 * - 批量扫描 Mapper 所在包：@MapperScan，此时 Mapper 类上可不加 @Mapper
 * @author Alex McAvoy
 * @date 2022/09/28 22:11
 * @version 1.0
 **/
@MapperScan(value = "com.dao")
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
