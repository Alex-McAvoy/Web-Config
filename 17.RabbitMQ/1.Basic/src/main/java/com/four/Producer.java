package com.four;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * 消息持久化，生产者
 * @author Alex McAvoy
 * @date 2022/10/8 19:04
 * @version 1.0
 **/
public class Producer {
    public static final String DURABLE_QUEUE_NAME = "durable_queue";

    /** 消息持久化
     *   1.消息队列持久化：将消息队列持久化
     *   2.消息持久化：将消息持久化
     * **/
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        /** 消息队列持久化
         *   - 队列持久化：RabbitMQ 重启后，消息队列依然存在
         *   - 若之前声明的对象是非持久化的，改为持久化后会报错，需要删除后重新创建
         * **/
        boolean durable = true;
        channel.queueDeclare(DURABLE_QUEUE_NAME,durable,false,false,null);

        System.out.println("请输入消息:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();

            /** 消息持久化
             *   - 消息持久化：RabbitMQ 重启后，消息依然存在
             *   - MessageProperties.PERSISTENT_TEXT_PLAIN
             *   - 不能保证完全不丢失消息，在消息刚准备存储磁盘，但还未存储完时，消息处于缓存的一个间隔点
             * **/
            channel.basicPublish("",DURABLE_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes());
            System.out.println("消息发送完成:" + message);
        }

    }

}
