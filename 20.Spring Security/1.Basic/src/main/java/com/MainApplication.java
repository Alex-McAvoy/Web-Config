package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


/**
 * SpringBoot 整合 SpringSecurity
 * 开启 SpringSecurity 注解支持 @EnableGlobalMethodSecurity
 *  - securedEnable = true : 开启 @Secured 注解支持
 *  - prePostEnabled = true: 开启 @PreAuthorize 和 @PostAuthorize 注解支持
 * @author Alex McAvoy
 * @date 2022/10/10 22:23
 * @version 1.0
 **/
@SpringBootApplication
@MapperScan("com.dao")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
