package com.three;

import com.rabbitmq.client.Channel;
import com.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * @Description: 消息手动应答的生产者，消息不丢失、出错时放回队列重新消费
 * @Author: Alex McAvoy
 * @Date: 2022/10/8 17:31
 * @Version: 1.0
 **/
public class Producer {
    public static final String TASK_QUEUE_NAME = "ack_queue";

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
