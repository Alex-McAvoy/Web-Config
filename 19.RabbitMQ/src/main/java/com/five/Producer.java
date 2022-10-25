package com.five;

import com.rabbitmq.client.Channel;
import com.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * @Description: 不公平分发的生产者
 * @Author: Alex McAvoy
 * @Date: 2022/10/8 17:31
 * @Version: 1.0
 **/
public class Producer {
    public static final String TASK_QUEUE_NAME = "unfair_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        channel.queueDeclare(TASK_QUEUE_NAME,false,false,false,null);

        //从控制台中接受信息并发送消息
        System.out.println("请输入消息:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes());
            System.out.println("消息发送完成:" + message);
        }

    }
}
