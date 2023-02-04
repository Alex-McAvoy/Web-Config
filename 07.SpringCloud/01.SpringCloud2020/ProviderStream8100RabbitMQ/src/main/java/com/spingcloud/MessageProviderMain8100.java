package com.spingcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Stream消息驱动生产者模块主启动类，消息队列采用RabbitMQ
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/1/31 11:15
 **/

@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MessageProviderMain8100 {
    public static void main(String[] args) {
        SpringApplication.run(MessageProviderMain8100.class, args);
    }
}
