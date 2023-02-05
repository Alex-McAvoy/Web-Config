package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 生产者-支付模块主启动类，服务注册采用Nacos
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/4 15:38
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8003.class, args);
    }
}
