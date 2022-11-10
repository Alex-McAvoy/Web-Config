package com.three;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;
import com.utils.SleepUtils;

/**
 * 消息手动应答的工作线程01，消息不丢失、出错时放回队列重新消费
 * @author Alex McAvoy
 * @date 2022/10/8 17:27
 * @version 1.0
 **/
public class Worker01 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        System.out.println("Worker01 等待接收消息，处理时间短");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //沉睡1秒
            SleepUtils.sleep(1);

            String message = new String(delivery.getBody());
            System.out.println("接收到的消息：" + message);

            /** 消息手动应答
             *   1.消息标记 tag
             *   2.是否批量应答，false 为只应答接收到的消息，true 为应答所有传递过来的消息（可能出现消息丢失）
             * **/
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "取消消费接口回调逻辑");
        };


		// 手动应答
        boolean autoAck = false; 
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, cancelCallback);
    }
}
