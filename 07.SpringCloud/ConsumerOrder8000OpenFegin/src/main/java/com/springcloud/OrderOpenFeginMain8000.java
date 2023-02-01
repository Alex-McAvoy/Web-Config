package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 消费者-订单模块主启动类，负载均衡采用OpenFegin
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 15:57
 **/
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OrderOpenFeginMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeginMain8000.class, args);
    }
}
