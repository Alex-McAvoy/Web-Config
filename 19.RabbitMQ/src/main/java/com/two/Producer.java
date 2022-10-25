package com.two;

import com.rabbitmq.client.Channel;
import com.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * @Description: 生产者，大量发消息，以让工作线程轮询
 * @Author: Alex McAvoy
 * @Date: 2022/10/8 17:05
 * @Version: 1.0
 **/
public class Producer {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //从控制台中接受信息并发送消息
        System.out.println("请输入消息:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("消息发送完成:" + message);
        }

    }
}
