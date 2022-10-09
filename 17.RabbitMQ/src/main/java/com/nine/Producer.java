package com.nine;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.utils.RabbitMQUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 基于死信的延时队列生产者
 * @Author: Alex McAvoy
 * @Date: 2022/10/9 17:16
 * @Version: 1.0
 **/
public class Producer {

    public static final String NORMAL_EXCHANGE = "X"; //普通交换机
    public static final String NORMAL_QUEUE_A = "QA"; //普通队列A，接收ddl为10s的消息
    public static final String NORMAL_QUEUE_B = "QB"; //普通队列B，接收ddl为40s的消息
    public static final String NORMAL_QUEUE_C = "QC"; //普通队列C，接收不设置ddl的消息
    public static final String DEAD_EXCHANGE = "Y"; //死信交换机
    public static final String DEAD_QUEUE = "QD"; //死信队列

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        //声明普通交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        //声明死信交换机
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        //声明死信队列
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);

        //声明普通队列A
        Map<String, Object> mapA = new HashMap<>();
        mapA.put("x-dead-letter-exchange", DEAD_EXCHANGE); //设置死信交换机
        mapA.put("x-dead-letter-routing-key", "YD"); //设置 routingKey
        mapA.put("x-message-ttl", 10000); //设置 ddl
        channel.queueDeclare(NORMAL_QUEUE_A, false, false, false, mapA);

        //声明普通队列B
        Map<String, Object> mapB = new HashMap<>();
        mapB.put("x-dead-letter-exchange", DEAD_EXCHANGE); //设置死信交换机
        mapB.put("x-dead-letter-routing-key", "YD"); //设置 routingKey
        mapB.put("x-message-ttl", 40000); //设置 ddl
        channel.queueDeclare(NORMAL_QUEUE_B, false, false, false, mapB);

        //声明普通队列C
        Map<String, Object> mapC = new HashMap<>();
        mapC.put("x-dead-letter-exchange", DEAD_EXCHANGE); //设置死信交换机
        mapC.put("x-dead-letter-routing-key", "YD"); //设置 routingKey
        channel.queueDeclare(NORMAL_QUEUE_C, false, false, false, mapC);

        //普通队列A与普通交换机绑定
        channel.queueBind(NORMAL_QUEUE_A, NORMAL_EXCHANGE, "XA");
        //普通队列B与普通交换机绑定
        channel.queueBind(NORMAL_QUEUE_B, NORMAL_EXCHANGE, "XB");
        //普通队列C与普通交换机绑定
        channel.queueBind(NORMAL_QUEUE_C, NORMAL_EXCHANGE, "XC");
        //死信队列与死信交换机绑定
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "YD");

        //发送消息
        System.out.println("正在发送消息给队列 A、B、C");
        String messageA = "消息来自ttl为10s的队列";
        channel.basicPublish(NORMAL_EXCHANGE, "XA", null, messageA.getBytes());
        String messageB = "消息来自ttl为40s的队列";
        channel.basicPublish(NORMAL_EXCHANGE, "XB", null, messageB.getBytes());
        String messageC = "消息来自不设置ttl的队列";
        channel.basicPublish(NORMAL_EXCHANGE, "XC", null, messageC.getBytes());

    }
}
