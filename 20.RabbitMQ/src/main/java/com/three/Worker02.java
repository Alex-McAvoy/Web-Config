package com.three;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;
import com.utils.SleepUtils;

/**
 * @Description: 消息手动应答的工作线程02，消息不丢失、出错时放回队列重新消费
 * @Author: Alex McAvoy
 * @Date: 2022/10/8 17:27
 * @Version: 1.0
 **/
public class Worker02 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        System.out.println("Worker02 等待接收消息，处理时间长");

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //沉睡10秒
            SleepUtils.sleep(10);

            String message = new String(delivery.getBody());
            System.out.println("接收到的消息：" + message);

            /** 消息手动应答
             *   1.消息标记 tag
             *   2.是否批量应答，false 为只应答接收到的消息，true 为应答所有传递过来的消息（可能出现消息丢失）
             * **/
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };

        //取消消费的回调接口
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "取消消费接口回调逻辑");
        };

        boolean autoAck = false; // 手动应答
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, cancelCallback);
    }
}
