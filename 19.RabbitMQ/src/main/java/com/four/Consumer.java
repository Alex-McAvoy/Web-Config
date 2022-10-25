package com.four;

import com.rabbitmq.client.*;
import com.utils.RabbitMQUtils;

/**
 * @Description: 消费者，收消息
 * @Author: Alex McAvoy
 * @Date: 2022/10/7 22:31
 * @Version: 1.0
 **/
public class Consumer {
    public static final String DURABLE_QUEUE_NAME = "durable_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        System.out.println("等待接收消息....");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(DURABLE_QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
