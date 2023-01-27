package com.one;

import com.rabbitmq.client.*;

/**
 * 消费者，收消息
 * @author Alex McAvoy
 * @date 2022/10/7 22:31
 * @version 1.0
 **/
public class Consumer {
    //队列名
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("root");
        factory.setPassword("123456");

        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        System.out.println("等待接收消息....");

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };

        //取消消费的回调接口
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };

        /** 消费者消费消息
         *   1.消费哪个队列
         *   2.消费成功之后是否要自动应答，true 为自动应答，false 为手动应答
         *   3.消费者成功消费的回调
         *   4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
