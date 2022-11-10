package com.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者，发消息
 * @author Alex McAvoy
 * @date 2022/10/7 22:18
 * @version 1.0
 **/
public class Producer {
    //队列名称
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("root");
        factory.setPassword("123456");

        //创建连接 connection
        Connection connection = factory.newConnection();
        //创建信道 channel
        Channel channel = connection.createChannel();

        /** 生成队列
         *   1.队列名称
         *   2.队列里面的消息是否持久化，默认消息存储在内存中
         *   3.该队列是否只供一个消费者进行消费，是否进行消息共享，若为 true 允许多个消费者消费
         *   4.在最后一个消费者断开连接以后，该队列是否自动删除，若为 true 则断开连接后自动删除
         *   5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //生成消息内容
        String message = "hello world";

        /** 发送消息
         *   1.发送到哪个交换机
         *   2.路由的 key
         *   3.其他的参数信息
         *   4.发送消息的消息体
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println("消息发送完毕");
    }
}
