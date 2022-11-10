package com.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ 工具类
 * @author Alex McAvoy
 * @date 2022/10/8 16:53
 * @version 1.0
 **/
public class RabbitMQUtils {

    /**
     * 创建一个连接的信道 channel
     * @return com.rabbitmq.client.Channel
     * @author Alex McAvoy
     * @date 2022/10/8 16:54
     **/
    public static Channel getChannel() throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("root");
        factory.setPassword("123456");

        //创建连接 connection
        Connection connection = factory.newConnection();
        //创建信道 channel
        Channel channel = connection.createChannel();

        return channel;
    }
}
