package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @SpringBootApplication: 标注主程序类，说明是一个 SpringBoot 应用
 * 可使用 @ImportResource 注解，来使用编写的Spring配置文件 beans.xml
 * 但 SpringBoot 不推荐使用，推荐使用配置类
 * @MapperScan: 批量扫描 Mapper 所在包，此时 Mapper 类上可不加 @Mapper 注解
 * @EnableCaching: 开启基于注解的缓存
 * @EnableAsync: 开启异步注解支持
 * @EnableScheduling: 开启定时任务注解支持
 * **/
//@MapperScan(value = "com.mapper")
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class MainApplication {
    public static void main(String[] args) {
        //Spring 应用启动
        SpringApplication.run(MainApplication.class,args);
    }
}
