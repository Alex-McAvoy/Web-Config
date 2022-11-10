package com.delayed.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 基于 rabbitmq_delayed_message_exchange 插件的延迟队列的消费者
 * @author Alex McAvoy
 * @date 2022/10/10 11:58
 * @version 1.0
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
