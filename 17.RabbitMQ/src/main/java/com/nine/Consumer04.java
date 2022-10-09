package com.nine;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;

/**
 * @Description: 基于死信的延时队列，消费者04，死信队列
 * @Author: Alex McAvoy
 * @Date: 2022/10/9 17:34
 * @Version: 1.0
 **/
public class Consumer04 {
    public static final String DEAD_QUEUE = "QD"; //死信队列

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        System.out.println("Consumer04 等待接收消息");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Consumer04 接收到的消息：" + message);
        };
        channel.basicConsume(DEAD_QUEUE, true, deliverCallback, consumerTag->{});

    }
}
