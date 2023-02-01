package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 消费者-订单模块主启动类，负载均衡采用LoadBalanced
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 15:57
 **/
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OrderLoadBalancedMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(OrderLoadBalancedMain8000.class, args);
    }
}
