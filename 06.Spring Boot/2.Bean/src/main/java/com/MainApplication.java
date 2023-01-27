package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/** SpringBoot 的 Bean 和 Thymeleaf
 * @author Alex McAvoy
 * @date 2022/11/10 11:02
 * @version 1.0
 **/
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
