package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 整合 RabbitMQ
 * - @EnableRabbit: 开启基于注解的 RabbitMQ
 * @author Alex McAvoy
 * @date 2022/10/10 11:23
 * @version 1.0
 **/
@EnableRabbit
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
