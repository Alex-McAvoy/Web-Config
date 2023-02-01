package com.delayed.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于TTL的延时队列配置类
 *  - 队列内部元素有序，在指定时间到后，或指定时间到前，将消息取出或处理
 *  - 本质上是设定 TTL 的死信队列
 *  - 只会检查队列首部的消息时否过期，如果第一个消息的延时时延很长，
 *    第二个消息的延时时延很短，第二个消息并不会优先执行
 * @author Alex McAvoy
 * @date 2022/10/10 11:35
 * @version 1.0
 **/
@Configuration
public class BaseTTLConfig {

	//普通交换机X
    public static final String NORMAL_EXCHANGE = "X"; 
	//普通队列A，接收ddl为10s的消息
    public static final String NORMAL_QUEUE_A = "QA"; 
	//普通队列B，接收ddl为40s的消息
    public static final String NORMAL_QUEUE_B = "QB"; 
	//普通队列C，接收不设置ddl的消息
    public static final String NORMAL_QUEUE_C = "QC"; 
	//死信交换机Y
    public static final String DEAD_EXCHANGE = "Y"; 
	//死信队列D
    public static final String DEAD_QUEUE = "QD";

    /**
     * 声明普通交换机X
     * @return org.springframework.amqp.core.DirectExchange
     * @author Alex McAvoy
     * @date 2022/10/10 11:48
     **/
    @Bean("exchangeX")
    public DirectExchange exchangeX() {
        return new DirectExchange(NORMAL_EXCHANGE);
    }

    /**
     * 声明死信交换机Y
     * @return org.springframework.amqp.core.DirectExchange
     * @author Alex McAvoy
     * @date 2022/10/10 11:48
     **/
    @Bean("exchangeY")
    public DirectExchange exchangeY() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    /**
     * 声明普通队列A
     * @return org.springframework.amqp.core.Queue
     * @author Alex McAvoy
     * @date 2022/10/10 11:48
     **/
    @Bean("queueA")
    public Queue queueA() {
        Map<String,Object> map = new HashMap<>();
		//设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE); 
		//设置 routingKey
        map.put("x-dead-letter-routing-key", "YD"); 
		//设置 ddl
        map.put("x-message-ttl", 10000); 
        return QueueBuilder.durable(NORMAL_QUEUE_A).withArguments(map).build();
    }

    /**
     * 声明普通队列B
     * @return org.springframework.amqp.core.Queue
     * @author Alex McAvoy
     * @date 2022/10/10 11:48
     **/
    @Bean("queueB")
    public Queue queueB() {
        Map<String,Object> map = new HashMap<>();
		//设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE); 
		//设置 routingKey
        map.put("x-dead-letter-routing-key", "YD"); 
		//设置 ddl
        map.put("x-message-ttl", 40000); 
        return QueueBuilder.durable(NORMAL_QUEUE_B).withArguments(map).build();
    }

    /**
     * 声明普通队列C
     * @return org.springframework.amqp.core.Queue
     * @author Alex McAvoy
     * @date 2022/10/10 11:48
     **/
    @Bean("queueC")
    public Queue queueC() {
        Map<String,Object> map = new HashMap<>();
		//设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE); 
		//设置 routingKey
        map.put("x-dead-letter-routing-key", "YD"); 
        return QueueBuilder.durable(NORMAL_QUEUE_C).withArguments(map).build();
    }

    /**
     * 声明死信队列
     * @return org.springframework.amqp.core.Queue
     * @author Alex McAvoy
     * @date 2022/10/10 11:49
     **/
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    /**
     * 普通队列A与普通交换机绑定
     * @param queueA 普通队列A
	 * @param exchangeX 普通交换机
     * @return org.springframework.amqp.core.Binding
     * @author Alex McAvoy
     * @date 2022/10/10 11:49
     **/
    @Bean
    public Binding queueABingExchangeX(@Qualifier("queueA") Queue queueA,
                                       @Qualifier("exchangeX")  DirectExchange exchangeX) {
        return BindingBuilder.bind(queueA).to(exchangeX).with("XA");
    }

    /**
     * 普通队列B与普通交换机绑定
     * @param queueB 普通队列B
	 * @param exchangeX 普通交换机
     * @return org.springframework.amqp.core.Binding
     * @author Alex McAvoy
     * @date 2022/10/10 11:49
     **/
    @Bean
    public Binding queueBBingExchangeX(@Qualifier("queueB") Queue queueB,
                                       @Qualifier("exchangeX")  DirectExchange exchangeX) {
        return BindingBuilder.bind(queueB).to(exchangeX).with("XB");
    }

    /**
     * 普通队列C与普通交换机绑定
     * @param queueC 普通队列C
	 * @param exchangeX 普通交换机
     * @return org.springframework.amqp.core.Binding
     * @author Alex McAvoy
     * @date 2022/10/10 11:49
     **/
    @Bean
    public Binding queueCBingExchangeX(@Qualifier("queueC") Queue queueC,
                                       @Qualifier("exchangeX")  DirectExchange exchangeX) {
        return BindingBuilder.bind(queueC).to(exchangeX).with("XC");
    }

    /**
     * 死信队列与死信交换机绑定
	 * @param queueD 死信队列
	 * @param exchangeY 死信交换机
     * @return org.springframework.amqp.core.Binding
     * @author Alex McAvoy
     * @date 2022/10/10 11:50
     **/
    @Bean
    public Binding queueDBingExchangeY(@Qualifier("queueD") Queue queueD,
                                       @Qualifier("exchangeY")  DirectExchange exchangeY) {
        return BindingBuilder.bind(queueD).to(exchangeY).with("YD");
    }

}
