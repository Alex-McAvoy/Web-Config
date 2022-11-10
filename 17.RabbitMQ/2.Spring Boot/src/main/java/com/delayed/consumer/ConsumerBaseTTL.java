package com.delayed.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 基于TTL的延迟队列的消费者
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 11:58
 * @Version: 1.0
 **/
@Component
public class ConsumerBaseTTL {
    @RabbitListener(queues = "QD")
    public void receiveD(Message message) {
        String msg = new String(message.getBody());
        System.out.println("收到延迟队列信息：" + msg);
    }
}
