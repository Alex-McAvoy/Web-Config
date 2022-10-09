package com.nine;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;

/**
 * @Description: 基于死信的延时队列，消费者02，队列B
 * @Author: Alex McAvoy
 * @Date: 2022/10/9 17:34
 * @Version: 1.0
 **/
public class Consumer02 {
    public static final String NORMAL_QUEUE_B = "QB"; //普通队列B，接收ddl为40s的消息

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        System.out.println("Consumer01 等待接收消息");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Consumer01 接收到的消息：" + message);
        };
        channel.basicConsume(NORMAL_QUEUE_B, true, deliverCallback, consumerTag->{});

    }
}
