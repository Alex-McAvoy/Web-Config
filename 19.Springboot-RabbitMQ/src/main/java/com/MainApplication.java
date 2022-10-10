package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: TODO
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 11:23
 * @Version: 1.0
 **/
@EnableRabbit //开启基于注解的 RabbitMQ
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        //Spring 应用启动
        SpringApplication.run(MainApplication.class,args);
    }
}
