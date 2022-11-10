package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * MVC 配置、登录拦截、自定义异常
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/11/10 9:14
 **/
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
