package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 开启 SpringSecurity 注解支持 @EnableGlobalMethodSecurity
 *  - securedEnable = true : 开启 @Secured 注解支持
 *  - prePostEnabled = true: 开启 @PreAuthorize 和 @PostAuthorize 注解支持
 **/
@SpringBootApplication
@MapperScan("com.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.MainApplication.class,args);
    }
}
