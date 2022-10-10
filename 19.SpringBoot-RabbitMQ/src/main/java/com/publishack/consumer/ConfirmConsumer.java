package com.publishack.consumer;

import com.publishack.config.ConfirmConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 高级发布确认消费者
 * @Author: Alex McAvoy
 * @Date: 2022/10/10 19:26
 * @Version: 1.0
 **/
@Component
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE)
    public void receiveConfirmMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println("接收到的消息:" + msg);
    }
}
