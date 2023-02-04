package com.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * 消息消费者接口
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2023/2/2 21:43
 **/
@Slf4j
@Service
public class MessageConsumerService {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 函数式编程接收消息，要求方法名与对应的input通道名对应
     *
     * @return java.util.function.Consumer<java.lang.String>
     * @author Alex McAvoy
     * @date 2023/2/2 21:50
     **/
    @Bean
    public Consumer<String> send() {
        return message -> {
            log.info("消费者端口" + serverPort + "消费消息: " + message);
        };
    }
}
