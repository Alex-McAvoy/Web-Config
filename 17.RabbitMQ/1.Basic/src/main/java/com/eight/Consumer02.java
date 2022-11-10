package com.eight;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信消费者02，死信队列
 * @author Alex McAvoy
 * @date 2022/10/9 14:58
 * @version 1.0
 **/
public class Consumer02 {
    public static final String DEAD_QUEUE_NAME = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        //---------等待接收消息---------
        System.out.println("Consumer02 等待接收消息");

        //---------接收消息---------
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Consumer02 接收到的消息：" + message);
        };
        channel.basicConsume(DEAD_QUEUE_NAME, true, deliverCallback, consumerTag->{});

    }
}
