package com.five;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.utils.RabbitMQUtils;
import com.utils.SleepUtils;

/**
 * 不公平分发的工作线程02
 * @author Alex McAvoy
 * @date 2022/10/8 17:27
 * @version 1.0
 **/
public class Worker02 {
    public static final String TASK_QUEUE_NAME = "unfair_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        System.out.println("Worker02 等待接收消息，处理时间长");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            SleepUtils.sleep(10);

            String message = new String(delivery.getBody());
            System.out.println("接收到的消息：" + message);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "取消消费接口回调逻辑");
        };

        /** 设置不公平分发
         *   - 通过信道的预取值 prefetch 来设定
         *   - 默认为 0，代表公平分发，但这样执行快的工作线程可能会陷入空等状态
         *   - 设置为 1，代表不公平分发，会根据实际情况来令工作线程接收消息，尽量不让工作线程陷入空等
         *   - 设置为具体的数值，代表设定预取值，会根据该值为信道分配要处理的消息条数
         * **/
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, cancelCallback);
    }
}
