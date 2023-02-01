package com.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;

/**
 * 工作线程02，相当于消费者
 * @author Alex McAvoy
 * @date 2022/10/8 16:55
 * @version 1.0
 **/
public class Worker02 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("接收到的消息：" + message);
        };

        //取消消费的回调接口
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "取消消费接口回调逻辑");
        };

        System.out.println("Worker2 等待接收消息");

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
