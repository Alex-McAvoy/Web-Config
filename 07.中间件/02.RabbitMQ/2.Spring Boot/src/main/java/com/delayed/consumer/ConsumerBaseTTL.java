package com.delayed.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 基于TTL的延迟队列的消费者
 * @author Alex McAvoy
 * @date 2022/10/10 11:58
 * @version 1.0
 **/
@Component
public class ConsumerBaseTTL {
    @RabbitListener(queues = "QD")
    public void receiveD(Message message) {
        String msg = new String(message.getBody());
        System.out.println("收到延迟队列信息：" + msg);
    }
}
