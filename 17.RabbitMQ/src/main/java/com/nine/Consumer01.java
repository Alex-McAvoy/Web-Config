package com.nine;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;

/**
 * @Description: 基于死信的延时队列，消费者01，队列A
 * @Author: Alex McAvoy
 * @Date: 2022/10/9 17:34
 * @Version: 1.0
 **/
public class Consumer01 {
    public static final String NORMAL_QUEUE_A = "QA"; //普通队列A，接收ddl为10s的消息

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        System.out.println("Consumer01 等待接收消息");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Consumer01 接收到的消息：" + message);
        };
        channel.basicConsume(NORMAL_QUEUE_A, true, deliverCallback, consumerTag->{});

    }
}
