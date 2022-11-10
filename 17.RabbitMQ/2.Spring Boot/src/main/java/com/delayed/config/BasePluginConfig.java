package com.delayed.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于rabbitmq_delayed_message_exchange插件的延迟队列配置类
 * @author Alex McAvoy
 * @date 2022/10/10 11:29
 * @version 1.0
 **/
@Configuration
public class BasePluginConfig {
    public static final String DELAYED_QUEUE = "delayed.queue";
    public static final String DELAYED_EXCHANGE = "delayed.exchange";
    public static final String DELAYED_ROUTING_KEY = "delayed.routingKey";

    /**
     * 声明时延队列
     * @return org.springframework.amqp.core.Queue
     * @author Alex McAvoy
     * @date 2022/10/10 13:56
     **/
    @Bean
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE);
    }

    /**
     * 自定义插件中新增的x-delayed-message类型交换机
     * @return org.springframework.amqp.core.CustomExchange
     * @author Alex McAvoy
     * @date 2022/10/10 13:56
     **/
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return new CustomExchange(DELAYED_EXCHANGE,"x-delayed-message",true,false,map);
    }

    /**
     * 绑定时延队列与时延交换机
     * @param queue 时延队列
	 * @param delayedExchange 时延交换机
     * @return org.springframework.amqp.core.Binding
     * @author Alex McAvoy
     * @date 2022/10/10 13:56
     **/
    @Bean
    public Binding bindingDelayedQueue(@Qualifier("delayedQueue") Queue queue,
                                       @Qualifier("delayedExchange") CustomExchange delayedExchange) {
        return BindingBuilder.bind(queue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
