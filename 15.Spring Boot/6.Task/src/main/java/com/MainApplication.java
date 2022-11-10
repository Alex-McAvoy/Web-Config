package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot 任务
 *  - 开启异步注解支持: @EnableAsync
 *  - 开启定时任务注解支持: @EnableScheduling
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/18 13:28
 **/
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
