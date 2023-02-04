package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Stream消息驱动消费者模块主启动类，消息队列采用RabbitMQ
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/2 21:41
 **/

@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MessageConsumerMain8101 {
    public static void main(String[] args) {
        SpringApplication.run(MessageConsumerMain8101.class, args);
    }
}
