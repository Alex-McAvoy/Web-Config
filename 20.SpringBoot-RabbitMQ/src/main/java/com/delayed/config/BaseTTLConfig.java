package com.delayed.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 基于TTL的延时队列配置类
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 11:35
 * @Version: 1.0
 **/

/** 基于TTL的延迟队列
 *   - 队列内部元素有序，在指定时间到后，或指定时间到前，将消息取出或处理
 *   - 本质上是设定 TTL 的死信队列
 *   - 只会检查队列首部的消息时否过期，如果第一个消息的延时时延很长，第二个消息的延时时延很短，第二个消息并不会优先执行
 * **/
@Configuration
public class BaseTTLConfig {


    public static final String NORMAL_EXCHANGE = "X"; //普通交换机X
    public static final String NORMAL_QUEUE_A = "QA"; //普通队列A，接收ddl为10s的消息
    public static final String NORMAL_QUEUE_B = "QB"; //普通队列B，接收ddl为40s的消息
    public static final String NORMAL_QUEUE_C = "QC"; //普通队列C，接收不设置ddl的消息
    public static final String DEAD_EXCHANGE = "Y"; //死信交换机Y
    public static final String DEAD_QUEUE = "QD"; //死信队列D

    /**
     * @Description: 声明普通交换机X
     * @Param: []
     * @Return: org.springframework.amqp.core.DirectExchange
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:48
     **/
    @Bean("exchangeX")
    public DirectExchange exchangeX() {
        return new DirectExchange(NORMAL_EXCHANGE);
    }

    /**
     * @Description: 声明死信交换机Y
     * @Param: []
     * @Return: org.springframework.amqp.core.DirectExchange
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:48
     **/
    @Bean("exchangeY")
    public DirectExchange exchangeY() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    /**
     * @Description: 声明普通队列A
     * @Param: []
     * @Return: org.springframework.amqp.core.Queue
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:48
     **/
    @Bean("queueA")
    public Queue queueA() {
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE); //设置死信交换机
        map.put("x-dead-letter-routing-key", "YD"); //设置 routingKey
        map.put("x-message-ttl", 10000); //设置 ddl
        return QueueBuilder.durable(NORMAL_QUEUE_A).withArguments(map).build();
    }

    /**
     * @Description: 声明普通队列B
     * @Param: []
     * @Return: org.springframework.amqp.core.Queue
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:48
     **/
    @Bean("queueB")
    public Queue queueB() {
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE); //设置死信交换机
        map.put("x-dead-letter-routing-key", "YD"); //设置 routingKey
        map.put("x-message-ttl", 40000); //设置 ddl
        return QueueBuilder.durable(NORMAL_QUEUE_B).withArguments(map).build();
    }

    /**
     * @Description: 声明普通队列C
     * @Param: []
     * @Return: org.springframework.amqp.core.Queue
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:48
     **/
    @Bean("queueC")
    public Queue queueC() {
        Map<String,Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE); //设置死信交换机
        map.put("x-dead-letter-routing-key", "YD"); //设置 routingKey
        return QueueBuilder.durable(NORMAL_QUEUE_C).withArguments(map).build();
    }

    /**
     * @Description: 声明死信队列
     * @Param: []
     * @Return: org.springframework.amqp.core.Queue
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:49
     **/
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    /**
     * @Description: 普通队列A与普通交换机绑定
     * @Param: [queueA, exchangeX]
     * @Return: org.springframework.amqp.core.Binding
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:49
     **/
    @Bean
    public Binding queueABingExchangeX(@Qualifier("queueA") Queue queueA,
                                       @Qualifier("exchangeX")  DirectExchange exchangeX) {
        return BindingBuilder.bind(queueA).to(exchangeX).with("XA");
    }

    /**
     * @Description: 普通队列B与普通交换机绑定
     * @Param: [queueB, exchangeX]
     * @Return: org.springframework.amqp.core.Binding
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:49
     **/
    @Bean
    public Binding queueBBingExchangeX(@Qualifier("queueB") Queue queueB,
                                       @Qualifier("exchangeX")  DirectExchange exchangeX) {
        return BindingBuilder.bind(queueB).to(exchangeX).with("XB");
    }

    /**
     * @Description: 普通队列C与普通交换机绑定
     * @Param: [queueC, exchangeX]
     * @Return: org.springframework.amqp.core.Binding
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:49
     **/
    @Bean
    public Binding queueCBingExchangeX(@Qualifier("queueC") Queue queueC,
                                       @Qualifier("exchangeX")  DirectExchange exchangeX) {
        return BindingBuilder.bind(queueC).to(exchangeX).with("XC");
    }

    /**
     * @Description: 死信队列与死信交换机绑定
     * @Param: [queueD, exchangeY]
     * @Return: org.springframework.amqp.core.Binding
     * @Author: Alex McAvoy
     * @Date: 2022/10/10 11:50
     **/
    @Bean
    public Binding queueDBingExchangeY(@Qualifier("queueD") Queue queueD,
                                       @Qualifier("exchangeY")  DirectExchange exchangeY) {
        return BindingBuilder.bind(queueD).to(exchangeY).with("YD");
    }

}
