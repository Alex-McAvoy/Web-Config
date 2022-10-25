package com.delayed.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 基于rabbitmq_delayed_message_exchange插件的延迟队列的消费者
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 11:58
 * @Version: 1.0
 **/
@Component
public class ConsumerBasePlugin {
    public static final String DELAYED_QUEUE = "delayed.queue";

    @RabbitListener(queues = DELAYED_QUEUE)
    public void receiveDelayedQueue(Message message) {
        String msg = new String(message.getBody());
        System.out.println("收到延迟队列信息：" + msg);
    }
}
